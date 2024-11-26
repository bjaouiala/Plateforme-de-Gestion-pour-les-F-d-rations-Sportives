package com.example.ala.event;

import com.example.ala.File.FileStorageService;
import com.example.ala.sport.PageResponse;
import com.example.ala.user.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final FileStorageService fileStorageService;

    public Long saveEvent(EventRequest eventRequest, MultipartFile multipartFile, Authentication connectedUser) {
        User user = (User) connectedUser.getPrincipal();
        var event = eventMapper.toEvent(eventRequest,multipartFile);
        event.setSport(user.getSport());

       return eventRepository.save(event).getId();
    }



    public PageResponse<EventResponse> GetEvents(int page, int size, Authentication connectedUser, String status) {
        System.out.println("here");
        User user = (User) connectedUser.getPrincipal();
        Pageable pageable = PageRequest.of(page,size, Sort.by("createdDate"));
        Status convertedStatus;
        try{
           convertedStatus = Status.valueOf(status);
        }catch (RuntimeException e){
            throw new RuntimeException("no status found for the given name");
        }

        Page<Event> eventPage = eventRepository.findFinishedEventsBySportName(user.getSport().getName(),pageable,convertedStatus);
        System.out.println("after query");
        List<EventResponse> eventResponseList = eventPage.stream().map(eventMapper::fromUser).toList();
        return new PageResponse<>(
                eventResponseList,
                eventPage.getNumber(),
                eventPage.getSize(),
                eventPage.getTotalElements(),
                eventPage.getTotalPages(),
                eventPage.isFirst(),
                eventPage.isLast()
        );
    }

    public PageResponse<EventResponse> GetAllEvents(int page, int size, Authentication connectedUser) {
        User user = (User) connectedUser.getPrincipal();
        Pageable pageable = PageRequest.of(page,size, Sort.by("createdDate"));
        Page<Event> eventPage = eventRepository.findAllEventsBySportName(user.getSport().getName(),pageable);
        List<EventResponse> eventResponseList = eventPage.stream().map(eventMapper::fromUser).toList();
        return new PageResponse<>(
                eventResponseList,
                eventPage.getNumber(),
                eventPage.getSize(),
                eventPage.getTotalElements(),
                eventPage.getTotalPages(),
                eventPage.isFirst(),
                eventPage.isLast()
        );
    }

    public EventResponse getEventById(Long id) {
        return eventMapper.fromUser(eventRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("there is no event for the given id")));
    }

    public Long updateEvent(Long id, EventRequest eventRequest, MultipartFile multipartFile) {
        Event event = eventRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("there is no event for the given id"));
        event.setEventPicture(fileStorageService.saveFile(multipartFile, event.getName()));
        event.setDescription(eventRequest.getDescription());
        event.setEndDate(eventRequest.getEndDate());
        event.setName(eventRequest.getName());
        event.setStatus(eventRequest.getStatus());
        event.setStartDate(eventRequest.getStartDate());
        event.setLocation(eventRequest.getLocation());
        return eventRepository.save(event).getId();

    }
}

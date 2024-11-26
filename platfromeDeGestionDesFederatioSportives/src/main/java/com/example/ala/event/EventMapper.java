package com.example.ala.event;

import com.example.ala.File.FileStorageService;
import com.example.ala.File.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class EventMapper {
    private final FileStorageService fileStorageService;
    public Event toEvent(EventRequest eventRequest, MultipartFile multipartFile) {
      return Event.builder()
                .name(eventRequest.getName())
                .description(eventRequest.getDescription())
                .location(eventRequest.getLocation())
                .status(Status.PENDING)
                .startDate(eventRequest.getStartDate())
                .endDate(eventRequest.getEndDate())
                .eventPicture(fileStorageService.saveFile(multipartFile, eventRequest.getName()))
                .build();

    }

    public EventResponse fromUser(Event event) {
        return EventResponse.builder()
                .id(event.getId())
                .name(event.getName())
                .createdBy(event.getCreatedBy())
                .description(event.getDescription())
                .endDate(event.getEndDate())
                .location(event.getLocation())
                .startDate(event.getStartDate())
                .eventPicture(FileUtils.readFileFromLocation(event.getEventPicture()))
                .status(event.getStatus())
                .build();
    }
}

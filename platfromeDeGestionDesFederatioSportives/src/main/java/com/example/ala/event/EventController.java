package com.example.ala.event;

import com.example.ala.sport.PageResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;
    @PostMapping

    public ResponseEntity<Long> addEvent(@Valid @RequestPart EventRequest eventRequest, @RequestParam("file")MultipartFile multipartFile,Authentication connectedUser){
        return ResponseEntity.ok(eventService.saveEvent(eventRequest,multipartFile,connectedUser));
    }
    @GetMapping
    public ResponseEntity<PageResponse<EventResponse>> GetEventByStatus(
            @RequestParam(name = "page",defaultValue = "0",required = false) int page,
            @RequestParam(name = "size",defaultValue = "1",required = false) int size,
            Authentication connectedUser,
            @RequestParam(name = "status") String status
    ){
        return ResponseEntity.ok(eventService.GetEvents(page,size,connectedUser,status));

    }
    @GetMapping("/all")
    public ResponseEntity<PageResponse<EventResponse>> GetAllEvent(
            @RequestParam(name = "page",defaultValue = "0",required = false) int page,
            @RequestParam(name = "size",defaultValue = "1",required = false) int size,
            Authentication connectedUser
    ){
        return ResponseEntity.ok(eventService.GetAllEvents(page,size,connectedUser));

    }
    @GetMapping("/{id}")
    public ResponseEntity<EventResponse> getEventById(@PathVariable("id") Long id){
        return ResponseEntity.ok(eventService.getEventById(id));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Long> updateEvent(@PathVariable("id") Long id,@Valid @RequestPart EventRequest eventRequest, @RequestParam("file")MultipartFile multipartFile){
        return ResponseEntity.ok(eventService.updateEvent(id,eventRequest,multipartFile));
    }

}

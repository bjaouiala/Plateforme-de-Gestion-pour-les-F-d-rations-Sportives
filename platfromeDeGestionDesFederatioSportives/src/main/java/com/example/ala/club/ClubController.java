package com.example.ala.club;

import com.example.ala.sport.PageResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("clubs")
@RequiredArgsConstructor
@Slf4j
public class ClubController {
    private final ClubService clubService;
    @GetMapping
    @Transactional
    public ResponseEntity<PageResponse<ClubResponse>>
    findClubBySport(
                    @RequestParam(name = "page",defaultValue = "0",required = false) int page,
                    @RequestParam(name = "size",defaultValue = "1",required = false) int size,
                    Authentication connectedUser){

        return ResponseEntity.ok(clubService.findClubBySport(connectedUser,page,size));
    }
    @PostMapping
    public ResponseEntity<Long> saveClub(@Valid @RequestPart ClubRequest request, @RequestParam("file") MultipartFile multipartFile, Authentication connectedUser){
        log.info("request"+request);
        return ResponseEntity.ok(clubService.saveClub(request,multipartFile,connectedUser));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ClubResponse> getClubById(@PathVariable("id") Long id){
        return ResponseEntity.ok(clubService.getBookById(id));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Long> updateClub(@PathVariable("id") Long id,@RequestPart ClubRequest request
            ,@RequestParam("file") MultipartFile multipartFile){
        return ResponseEntity.ok(clubService.updateClub(id,request,multipartFile));
    }
    @GetMapping("/club")
    public ResponseEntity<List<ClubResponse>> getAllClub(Authentication connectedUser){
        return ResponseEntity.ok(clubService.getAllClub(connectedUser));
    }
}

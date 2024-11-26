package com.example.ala.adminClub;

import com.example.ala.authentication.RegistrationRequest;
import com.example.ala.sport.PageResponse;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin-club")
public class AdminClubController {
    private final AdminClubService adminClubService;
    @GetMapping
    ResponseEntity<PageResponse<AdminResponse>> getAllClubAdmin(@RequestParam(name = "page",defaultValue = "0",required = false) Integer page,
                                                 @RequestParam(name = "size",defaultValue = "2",required = false) Integer size,
                                                 Authentication  connectedUser){
        return ResponseEntity.ok(adminClubService.getAllClubAdmin(page,size,connectedUser));
    }
    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<?> registerAdmin(@Valid @RequestPart RegistrationRequest request, @RequestParam("picture") MultipartFile picture,Authentication connectedUser) throws MessagingException {

        adminClubService.registerAdmin(request,picture,connectedUser);
        return ResponseEntity.accepted().build();
    }

    @PatchMapping
    ResponseEntity<Long> changeAccountStatus(@RequestParam(name = "id") Long id,@RequestParam("status") boolean accountLocked){
        return ResponseEntity.ok(adminClubService.changeAccountStatus(id,accountLocked));
    }
    @PatchMapping("/{id}")
    ResponseEntity<Long> updateAdminClub(@Valid @RequestPart RegistrationRequest request, @RequestParam("picture") MultipartFile picture,Authentication connectedUser,@PathVariable("id") Long id){
        return ResponseEntity.ok(adminClubService.updateAdminClub(request,picture,connectedUser,id));
    }
    @GetMapping("/{id}")
    ResponseEntity<AdminResponse> getAdminById(@PathVariable("id") Long id){
        return ResponseEntity.ok(adminClubService.getAdminById(id));
    }
}

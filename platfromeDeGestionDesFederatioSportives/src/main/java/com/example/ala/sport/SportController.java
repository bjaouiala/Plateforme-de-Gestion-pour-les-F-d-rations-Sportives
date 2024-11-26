package com.example.ala.sport;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sports")
public class SportController {
    private final SportService sportService;
    @PostMapping
    ResponseEntity<Long> addSport(@Valid @RequestBody SportRequest request){
        return ResponseEntity.ok(sportService.addSport(request));
    }
    @GetMapping
    ResponseEntity<PageResponse<SportResponse>> findAllSports(
            @RequestParam(name = "page",defaultValue = "0",required = false) int page,
            @RequestParam(name = "size",defaultValue = "0",required = false) int size

    ){
        return ResponseEntity.ok(sportService.findAllSports(page,size));
    }
}

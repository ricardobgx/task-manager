package com.taskmanager.profiles.api.me.controllers;

import com.taskmanager.profiles.api.me.dtos.UserDetailsDto;
import com.taskmanager.profiles.api.me.services.GetMeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("me")
@RequiredArgsConstructor
public class MeController {
    private final GetMeService getMeService;

    @GetMapping
    public ResponseEntity<UserDetailsDto> getMe(Authentication authentication) {
        UserDetailsDto userDetailsDto = this.getMeService.execute(authentication);

        return ResponseEntity.ok(userDetailsDto);
    }
}

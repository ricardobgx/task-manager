package com.taskmanager.profiles.api.me.dtos;

import java.time.LocalDate;
import java.util.List;

public record UserDetailsDto(
        String name,
        String email,
        LocalDate birthDate,
        String phone,
        String profilePictureUrl,
        List<String> roles,
        Long exp
) {
}

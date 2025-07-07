package com.taskmanager.profiles.api.profile.dtos;

import java.time.LocalDate;
import java.util.UUID;

public record CreateProfileInputDto(
        String username,
        LocalDate birthDate,
        String phone,
        UUID profilePictureId
) {
}

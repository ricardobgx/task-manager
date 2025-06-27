package com.taskmanager.bff.security.dtos;

import jakarta.validation.constraints.NotEmpty;

public record LoginOptionsDto(
        @NotEmpty String label,
        @NotEmpty String loginUri,
        boolean isSameAuthority
) {
}

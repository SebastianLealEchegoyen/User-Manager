package com.userupdater.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UpdateUserRequest(
        @NotBlank @Email String email,
        @NotBlank String fullName) {
}

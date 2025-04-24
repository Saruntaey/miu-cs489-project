package edu.miu.cs489.dentalms.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthenticationRequest(
        @NotBlank(message= "cannot be blank/empty/null")
        String username,
        @NotBlank(message= "cannot be blank/empty/null")
        String password
) {
}

package edu.miu.cs489.dentalms.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PatientRequestDto(
        @NotBlank(message= "cannot be blank/empty/null")
        String firstName,
        @NotBlank(message= "cannot be blank/empty/null")
        String lastName,
        @NotBlank(message= "cannot be blank/empty/null")
        String phone,
        @NotBlank(message= "cannot be blank/empty/null")
        @Email(message = "invalid email format")
        String email,
        @NotBlank(message= "cannot be blank/empty/null")
        String password,
        @Valid
        @NotNull(message="cannot be null")
        AddressRequestDto address
) {
}

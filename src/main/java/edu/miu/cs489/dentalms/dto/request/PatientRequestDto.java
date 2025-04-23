package edu.miu.cs489.dentalms.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record PatientRequestDto(
        String firstName,
        String lastName,
        String phone,
        String email,
        String password,
        @Valid
        @NotNull(message="cannot be null")
        AddressRequestDto address
) {
}

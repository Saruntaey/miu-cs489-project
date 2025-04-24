package edu.miu.cs489.dentalms.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SurgeryRequestDto (
        @NotBlank(message= "cannot be blank/empty/null")
        String name,
        @NotBlank(message= "cannot be blank/empty/null")
        String phone,
        @Valid
        @NotNull(message="cannot be null")
        AddressRequestDto address
){
}

package edu.miu.cs489.dentalms.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AddressRequestDto (
        @NotBlank(message= "cannot be blank/empty/null")
        String street,
        String city,
        String state,
        String zip
){
}

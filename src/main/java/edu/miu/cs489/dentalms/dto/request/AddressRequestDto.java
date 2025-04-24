package edu.miu.cs489.dentalms.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AddressRequestDto (
        @NotBlank(message= "cannot be blank/empty/null")
        String street,
        @NotBlank(message= "cannot be blank/empty/null")
        String city,
        @NotBlank(message= "cannot be blank/empty/null")
        String state,
        @NotBlank(message= "cannot be blank/empty/null")
        String zip
){
}

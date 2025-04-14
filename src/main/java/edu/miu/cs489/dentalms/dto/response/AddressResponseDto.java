package edu.miu.cs489.dentalms.dto.response;

public record AddressResponseDto (
        Long id,
        String street,
        String city,
        String state,
        String zip
){
}

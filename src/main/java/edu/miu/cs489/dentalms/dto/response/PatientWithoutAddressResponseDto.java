package edu.miu.cs489.dentalms.dto.response;

public record PatientWithoutAddressResponseDto (
        Long id,
        String firstName,
        String lastName,
        String phone,
        String email
){
}

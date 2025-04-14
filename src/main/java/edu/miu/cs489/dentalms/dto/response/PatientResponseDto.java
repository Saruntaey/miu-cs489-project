package edu.miu.cs489.dentalms.dto.response;


public record PatientResponseDto(
        Long id,
        String firstName,
        String lastName,
        String phone,
        String email,
        AddressResponseDto address
) {
}

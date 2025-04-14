package edu.miu.cs489.dentalms.dto.request;

public record PatientRequestDto(
        String firstName,
        String lastName,
        String phone,
        String email,
        AddressRequestDto address
) {
}

package edu.miu.cs489.dentalms.dto.response;

public record DentistResponseDto(
        Long id,
        String firstName,
        String lastName,
        String phone,
        String email,
        String specialization
) {
}

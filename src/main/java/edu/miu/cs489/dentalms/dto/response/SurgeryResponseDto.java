package edu.miu.cs489.dentalms.dto.response;

public record SurgeryResponseDto(
        Long id,
        String name,
        String phone,
        AddressResponseDto address
) {
}

package edu.miu.cs489.dentalms.dto.request;

public record SurgeryRequestDto (
        String name,
        String phone,
        AddressRequestDto address
){
}

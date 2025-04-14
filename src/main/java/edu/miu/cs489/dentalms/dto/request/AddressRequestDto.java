package edu.miu.cs489.dentalms.dto.request;

import jakarta.persistence.Column;

public record AddressRequestDto (
    String street,
    String city,
    String state,
    String zip
){
}

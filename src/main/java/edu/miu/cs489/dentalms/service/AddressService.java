package edu.miu.cs489.dentalms.service;

import edu.miu.cs489.dentalms.dto.response.AddressWithPatientResponseDto;

import java.util.List;

public interface AddressService {
    List<AddressWithPatientResponseDto> getAllAddresses();
}

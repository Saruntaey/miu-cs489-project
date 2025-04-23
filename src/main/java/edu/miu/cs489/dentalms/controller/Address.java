package edu.miu.cs489.dentalms.controller;

import edu.miu.cs489.dentalms.dto.response.AddressWithPatientResponseDto;
import edu.miu.cs489.dentalms.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/addresses")
@RequiredArgsConstructor
public class Address {
    private final AddressService addressService;

    @GetMapping
    public List<AddressWithPatientResponseDto> getAllAddresses() {
        return addressService.getAllAddresses();
    }
}

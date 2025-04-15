package edu.miu.cs489.dentalms.service.impl;

import edu.miu.cs489.dentalms.dto.response.AddressWithPatientResponseDto;
import edu.miu.cs489.dentalms.mapper.AddressMapper;
import edu.miu.cs489.dentalms.repo.AddressRepo;
import edu.miu.cs489.dentalms.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepo addressRepo;
    private final AddressMapper addressMapper;

    @Override
    public List<AddressWithPatientResponseDto> getAllAddresses() {
        return addressRepo.findAll(Sort.by(Sort.Direction.ASC, "city")).stream().map(addressMapper::addressToAddressWithPatientResponseDto).collect(Collectors.toList());
    }
}

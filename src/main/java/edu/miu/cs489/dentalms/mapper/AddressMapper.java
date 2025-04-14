package edu.miu.cs489.dentalms.mapper;

import edu.miu.cs489.dentalms.dto.request.AddressRequestDto;
import edu.miu.cs489.dentalms.dto.response.AddressResponseDto;
import edu.miu.cs489.dentalms.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AddressMapper {
    Address addressRequestDtoToAddress(AddressRequestDto a);
    AddressResponseDto addressToAddressResponseDto(Address address);
}

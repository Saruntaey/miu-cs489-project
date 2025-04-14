package edu.miu.cs489.dentalms.mapper;

import edu.miu.cs489.dentalms.dto.request.SurgeryRequestDto;
import edu.miu.cs489.dentalms.dto.response.SurgeryResponseDto;
import edu.miu.cs489.dentalms.model.Surgery;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SurgeryMapper {
    Surgery surgeryRequestDtoToSurgery(SurgeryRequestDto surgeryRequestDto);
    SurgeryResponseDto surgeryToSurgeryResponseDto(Surgery surgery);
}

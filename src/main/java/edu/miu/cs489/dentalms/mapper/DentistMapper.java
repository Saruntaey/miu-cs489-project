package edu.miu.cs489.dentalms.mapper;

import edu.miu.cs489.dentalms.dto.request.DentistRequestDto;
import edu.miu.cs489.dentalms.dto.response.DentistResponseDto;
import edu.miu.cs489.dentalms.model.Dentist;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DentistMapper {
    Dentist dentistRequestDtoToDentist(DentistRequestDto dentist);
    DentistResponseDto dentistToDentistResponseDto(Dentist dentist);
}

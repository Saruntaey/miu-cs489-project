package edu.miu.cs489.dentalms.mapper;

import edu.miu.cs489.dentalms.dto.request.PatientRequestDto;
import edu.miu.cs489.dentalms.dto.response.PatientResponseDto;
import edu.miu.cs489.dentalms.model.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {AddressMapper.class})
public interface PatientMapper {
    Patient patientRequestDtoToPatient(PatientRequestDto p);
    PatientResponseDto patientToPatientResponseDto(Patient p);
}

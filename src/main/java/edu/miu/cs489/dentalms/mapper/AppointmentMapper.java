package edu.miu.cs489.dentalms.mapper;

import edu.miu.cs489.dentalms.dto.request.AppointmentRequestDto;
import edu.miu.cs489.dentalms.dto.request.ChangeAppointmentRequestDto;
import edu.miu.cs489.dentalms.dto.response.AppointmentResponseDto;
import edu.miu.cs489.dentalms.model.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {PatientMapper.class, DentistMapper.class, SurgeryMapper.class})
public interface AppointmentMapper {
    @Mappings({
        @Mapping(source="dentistId", target="dentist.id"),
        @Mapping(source="patientId", target="patient.id"),
        @Mapping(source="surgeryId", target="surgery.id")
    })
    Appointment appointmentRequestDtoToAppointment(AppointmentRequestDto dto);

    @Mappings({
            @Mapping(source="dentistId", target="dentist.id"),
            @Mapping(source="surgeryId", target="surgery.id")
    })
    Appointment changeAppointmentRequestDtoToAppointment(ChangeAppointmentRequestDto dto);
    AppointmentResponseDto appointmentToAppointmentResponseDto(Appointment appointment);
}

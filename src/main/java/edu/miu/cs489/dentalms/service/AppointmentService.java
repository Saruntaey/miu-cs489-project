package edu.miu.cs489.dentalms.service;

import edu.miu.cs489.dentalms.dto.request.AppointmentRequestDto;
import edu.miu.cs489.dentalms.dto.request.ChangeAppointmentRequestDto;
import edu.miu.cs489.dentalms.dto.request.PatientAppointmentRequestDto;
import edu.miu.cs489.dentalms.dto.response.AppointmentResponseDto;
import org.springframework.data.domain.Page;

public interface AppointmentService {
    AppointmentResponseDto patientCreateAppointment(PatientAppointmentRequestDto appointment);
    AppointmentResponseDto officeManagerCreateAppointment(AppointmentRequestDto appointment);
    Page<AppointmentResponseDto> getAllAppointments(int page, int pageSize, String sortBy, String sortDirection);
    AppointmentResponseDto changeAppointment(Long id, ChangeAppointmentRequestDto appointment);
    AppointmentResponseDto cancelAppointment(Long id);
}

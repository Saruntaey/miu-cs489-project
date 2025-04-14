package edu.miu.cs489.dentalms.service;

import edu.miu.cs489.dentalms.dto.request.AppointmentRequestDto;
import edu.miu.cs489.dentalms.dto.response.AppointmentResponseDto;

import java.util.List;

public interface AppointmentService {
    AppointmentResponseDto createAppointment(AppointmentRequestDto appointment);
    List<AppointmentResponseDto> getAllAppointments();
}

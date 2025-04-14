package edu.miu.cs489.dentalms.service.impl;

import edu.miu.cs489.dentalms.dto.request.AppointmentRequestDto;
import edu.miu.cs489.dentalms.dto.response.AppointmentResponseDto;
import edu.miu.cs489.dentalms.mapper.AppointmentMapper;
import edu.miu.cs489.dentalms.model.Appointment;
import edu.miu.cs489.dentalms.repo.AppointmentRepo;
import edu.miu.cs489.dentalms.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepo appointmentRepo;
    private final AppointmentMapper appointmentMapper;

    @Override
    public AppointmentResponseDto createAppointment(AppointmentRequestDto appointment) {
        Appointment a = appointmentMapper.appointmentRequestDtoToAppointment(appointment);
        System.out.println(a);
        return appointmentMapper.appointmentToAppointmentResponseDto(appointmentRepo.save(a));
    }

    @Override
    public List<AppointmentResponseDto> getAllAppointments() {
        return appointmentRepo.findAll().stream()
                .map(appointmentMapper::appointmentToAppointmentResponseDto)
                .toList();
    }
}

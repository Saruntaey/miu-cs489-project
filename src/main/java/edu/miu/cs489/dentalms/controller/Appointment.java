package edu.miu.cs489.dentalms.controller;

import edu.miu.cs489.dentalms.dto.request.AppointmentRequestDto;
import edu.miu.cs489.dentalms.dto.request.ChangeAppointmentRequestDto;
import edu.miu.cs489.dentalms.dto.request.PatientAppointmentRequestDto;
import edu.miu.cs489.dentalms.dto.response.AppointmentResponseDto;
import edu.miu.cs489.dentalms.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/appointments")
@RequiredArgsConstructor
public class Appointment {
    private final AppointmentService appointmentService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('OFFICE_MANAGER', 'PATIENT', 'DENTIST')")
    public Page<AppointmentResponseDto> getAllAppointments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection //for descending, desc
    ) {
        return appointmentService.getAllAppointments(page, pageSize, sortBy, sortDirection);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('OFFICE_MANAGER')")
    AppointmentResponseDto createAppointment(@Valid @RequestBody AppointmentRequestDto appointment) {
        return appointmentService.officeManagerCreateAppointment(appointment);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('PATIENT')")
    AppointmentResponseDto changeAppointment(@PathVariable("id") Long id, @Valid @RequestBody ChangeAppointmentRequestDto appointment) {
        return appointmentService.changeAppointment(id, appointment);
    }

    @PostMapping("/{id}/cancel")
    @PreAuthorize("hasAuthority('PATIENT')")
    AppointmentResponseDto cancelAppointment(@PathVariable("id") Long id) {
        return appointmentService.cancelAppointment(id);
    }

    @PostMapping("/patient")
    @PreAuthorize("hasAuthority('PATIENT')")
    AppointmentResponseDto patientCreateAppointment(@Valid @RequestBody PatientAppointmentRequestDto appointment) {
        return appointmentService.patientCreateAppointment(appointment);
    }

}

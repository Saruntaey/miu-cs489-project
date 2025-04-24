package edu.miu.cs489.dentalms.service.impl;

import edu.miu.cs489.dentalms.dto.request.AppointmentRequestDto;
import edu.miu.cs489.dentalms.dto.response.*;
import edu.miu.cs489.dentalms.mapper.AppointmentMapper;
import edu.miu.cs489.dentalms.model.*;
import edu.miu.cs489.dentalms.repo.AppointmentRepo;
import edu.miu.cs489.dentalms.repo.DentistRepo;
import edu.miu.cs489.dentalms.repo.PatientRepo;
import edu.miu.cs489.dentalms.repo.SurgeryRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class AppointmentServiceImplTest {
    @Mock
    private  AppointmentRepo appointmentRepo;
    @Mock
    private  AppointmentMapper appointmentMapper;
    @Mock
    private DentistRepo dentistRepo;
    @Mock
    private SurgeryRepo surgeryRepo;
    @Mock
    private PatientRepo patientRepo;

    @InjectMocks
    private AppointmentServiceImpl appointmentService;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("office manager create appointment")
    void officeManagerCreateAppointment() {
        Dentist d = new Dentist();
        d.setId(1L);

        Patient p = new Patient();
        p.setId(2L);
        p.setAddress(new Address());

        Surgery s = new Surgery();
        s.setId(3L);
        s.setAddress(new Address());

        Mockito.when(dentistRepo.findById(1L)).thenReturn(Optional.of(d));
        Mockito.when(patientRepo.findById(2L)).thenReturn(Optional.of(p));
        Mockito.when(surgeryRepo.findById(3L)).thenReturn(Optional.of(s));
        Mockito.when(appointmentRepo.save(Mockito.any(Appointment.class))).then(arg -> arg.getArgument(0));
        Mockito.when(appointmentMapper.appointmentRequestDtoToAppointment(Mockito.any(AppointmentRequestDto.class))).then(arg -> {
            AppointmentRequestDto dto = arg.getArgument(0);
            Appointment a = new Appointment();
            a.setDate(dto.date());
            a.setTime(dto.time());
            a.setDentist(new Dentist());
            a.getDentist().setId(dto.dentistId());
            a.setPatient(new Patient());
            a.getPatient().setId(dto.patientId());
            a.setSurgery(new Surgery());
            a.getSurgery().setId(dto.surgeryId());
            return a;
        });
        Mockito.when(appointmentMapper.appointmentToAppointmentResponseDto(Mockito.any(Appointment.class))).then( arg -> {
            Appointment a = arg.getArgument(0);
            return new AppointmentResponseDto(
                    a.getId(),
                    a.getDate(),
                    a.getTime(),
                    new DentistResponseDto(
                            a.getDentist().getId(),
                            a.getDentist().getFirstName(),
                            a.getDentist().getLastName(),
                            a.getDentist().getPhone(),
                            a.getDentist().getEmail(),
                            a.getDentist().getSpecialization()
                    ),
                    new PatientResponseDto(
                            a.getPatient().getId(),
                            a.getPatient().getFirstName(),
                            a.getPatient().getLastName(),
                            a.getPatient().getPhone(),
                            a.getPatient().getEmail(),
                            new AddressResponseDto(
                                    a.getPatient().getAddress().getId(),
                                    a.getPatient().getAddress().getStreet(),
                                    a.getPatient().getAddress().getCity(),
                                    a.getPatient().getAddress().getState(),
                                    a.getPatient().getAddress().getZip()
                            )
                    ),
                    new SurgeryResponseDto(
                            a.getSurgery().getId(),
                            a.getSurgery().getName(),
                            a.getSurgery().getPhone(),
                            new AddressResponseDto(
                                    a.getSurgery().getAddress().getId(),
                                    a.getSurgery().getAddress().getStreet(),
                                    a.getSurgery().getAddress().getCity(),
                                    a.getSurgery().getAddress().getState(),
                                    a.getSurgery().getAddress().getZip()
                            )
                    ),
                    a.getStatus()
            );
        });

        Assertions.assertDoesNotThrow(() -> {
            appointmentService.officeManagerCreateAppointment(new AppointmentRequestDto(
                    LocalDate.parse("2023-04-12"),
                    LocalTime.parse("16:35"),
                    1L,
                    2L,
                    3L
            ));
        });
        Mockito.verify(appointmentRepo, Mockito.times(1)).save(Mockito.any(Appointment.class));
    }
}
package edu.miu.cs489.dentalms.service.impl;

import edu.miu.cs489.dentalms.dto.request.AppointmentRequestDto;
import edu.miu.cs489.dentalms.dto.request.ChangeAppointmentRequestDto;
import edu.miu.cs489.dentalms.dto.request.PatientAppointmentRequestDto;
import edu.miu.cs489.dentalms.dto.response.AppointmentResponseDto;
import edu.miu.cs489.dentalms.exception.user.UnAuthorizeException;
import edu.miu.cs489.dentalms.exception.appointment.AppointmentCannotUpdate;
import edu.miu.cs489.dentalms.exception.appointment.AppointmentNotFoundException;
import edu.miu.cs489.dentalms.exception.dentist.DentistNotFoundException;
import edu.miu.cs489.dentalms.exception.patient.PatientNotFoundException;
import edu.miu.cs489.dentalms.exception.surgery.SurgeryNotFoundException;
import edu.miu.cs489.dentalms.mapper.AppointmentMapper;
import edu.miu.cs489.dentalms.model.Appointment;
import edu.miu.cs489.dentalms.model.AppointmentStatus;
import edu.miu.cs489.dentalms.model.Patient;
import edu.miu.cs489.dentalms.repo.AppointmentRepo;
import edu.miu.cs489.dentalms.repo.DentistRepo;
import edu.miu.cs489.dentalms.repo.PatientRepo;
import edu.miu.cs489.dentalms.repo.SurgeryRepo;
import edu.miu.cs489.dentalms.service.AppointmentService;
import edu.miu.cs489.dentalms.user.Role;
import edu.miu.cs489.dentalms.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepo appointmentRepo;
    private final AppointmentMapper appointmentMapper;
    private final DentistRepo dentistRepo;
    private final SurgeryRepo surgeryRepo;
    private final PatientRepo patientRepo;

    @Override
    public AppointmentResponseDto patientCreateAppointment(PatientAppointmentRequestDto a) {
        Patient p = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return officeManagerCreateAppointment(
                new AppointmentRequestDto(
                        a.date(),
                        a.time(),
                        a.dentistId(),
                        p.getId(),
                        a.surgeryId()
                )
        );
    }

    @Override
    public AppointmentResponseDto officeManagerCreateAppointment(AppointmentRequestDto appointment) {
        Appointment a = appointmentMapper.appointmentRequestDtoToAppointment(appointment);
        var p = patientRepo.findById(a.getPatient().getId());
        if (p.isEmpty()) {
            throw new PatientNotFoundException(a.getPatient().getId());
        }
        a.setPatient(p.get());
        resolveChild(a, p.get());
        return appointmentMapper.appointmentToAppointmentResponseDto(appointmentRepo.save(a));
    }

    @Override
    public Page<AppointmentResponseDto> getAllAppointments(int page, int pageSize, String sortBy, String sortDirection) {
        User p = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Pageable pageable = PageRequest.of(
                page,                                       //page number
                pageSize,                                   //page size
                Sort.Direction.fromString(sortDirection),   //sorting direction
                sortBy                                      //sort by which field
        );
        Page<Appointment> appointments = switch (p.getRole()) {
            case Role.PATIENT -> appointmentRepo.findAllByPatientId(p.getId(), pageable);
            case Role.DENTIST -> appointmentRepo.findAllByDentistId(p.getId(), pageable);
            case Role.OFFICE_MANAGER -> appointmentRepo.findAll(pageable);
        };

        return appointments.map(appointmentMapper::appointmentToAppointmentResponseDto);
    }

    @Override
    public AppointmentResponseDto changeAppointment(Long id, ChangeAppointmentRequestDto appointment) {
        Patient p = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        checkUpdatable(id, p);
        Appointment updated = appointmentMapper.changeAppointmentRequestDtoToAppointment(appointment);
        updated.setId(id);
        resolveChild(updated, p);
        return appointmentMapper.appointmentToAppointmentResponseDto(appointmentRepo.save(updated));
    }

    @Override
    public AppointmentResponseDto cancelAppointment(Long id) {
        Patient p = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Appointment a = checkUpdatable(id, p);
        a.setStatus(AppointmentStatus.CANCELLED);
        resolveChild(a, p);
        appointmentRepo.save(a);
        return appointmentMapper.appointmentToAppointmentResponseDto(a);
    }

    private Appointment checkUpdatable(Long id, Patient p) {
        Optional<Appointment> a =  appointmentRepo.findById(id);
        if (a.isEmpty()) {
            throw new AppointmentNotFoundException(id);
        }
        if (!a.get().getPatient().getId().equals( p.getId()))  {
            throw new UnAuthorizeException();
        }
        if (!a.get().getStatus().equals(AppointmentStatus.PENDING)) {
            throw new AppointmentCannotUpdate();
        }
        return a.get();
    }

    private void resolveChild(Appointment a, Patient p) {
        var d = dentistRepo.findById(a.getDentist().getId());
        if (d.isEmpty()) {
            throw new DentistNotFoundException(a.getDentist().getId());
        }
        var s = surgeryRepo.findById(a.getSurgery().getId());
        if (s.isEmpty()) {
            throw new SurgeryNotFoundException(a.getSurgery().getId());
        }
        a.setPatient(p);
        a.setDentist(d.get());
        a.setSurgery(s.get());
    }
}

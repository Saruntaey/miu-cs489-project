package edu.miu.cs489.dentalms.dto.response;

import edu.miu.cs489.dentalms.model.Dentist;
import edu.miu.cs489.dentalms.model.Patient;
import edu.miu.cs489.dentalms.model.Surgery;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;
import java.time.LocalTime;

public record AppointmentResponseDto (
        Long id,
        LocalDate date,
        LocalTime time,
        DentistResponseDto dentist,
        PatientResponseDto patient,
        SurgeryResponseDto surgery
){
}

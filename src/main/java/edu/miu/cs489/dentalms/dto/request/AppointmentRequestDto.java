package edu.miu.cs489.dentalms.dto.request;

import java.time.LocalDate;
import java.time.LocalTime;

public record AppointmentRequestDto (
        LocalDate date,
        LocalTime time,
        Long dentistId,
        Long patientId,
        Long surgeryId
){
}

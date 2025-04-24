package edu.miu.cs489.dentalms.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record PatientAppointmentRequestDto(
        @NotNull(message= "cannot be null")
        LocalDate date,
        @NotNull(message= "cannot be null")
        LocalTime time,
        @NotNull(message= "cannot be null")
        Long dentistId,
        @NotNull(message= "cannot be null")
        Long surgeryId
) {
}

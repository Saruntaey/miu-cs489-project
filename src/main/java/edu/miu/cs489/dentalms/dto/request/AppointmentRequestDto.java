package edu.miu.cs489.dentalms.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record AppointmentRequestDto (
        @NotNull(message= "cannot be null")
        LocalDate date,
        @NotNull(message= "cannot be null")
        LocalTime time,
        @NotNull(message= "cannot be null")
        Long dentistId,
        @NotNull(message= "cannot be null")
        Long patientId,
        @NotNull(message= "cannot be null")
        Long surgeryId
){
}

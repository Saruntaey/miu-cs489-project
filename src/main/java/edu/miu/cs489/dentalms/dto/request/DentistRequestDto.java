package edu.miu.cs489.dentalms.dto.request;

import edu.miu.cs489.dentalms.model.Appointment;

import java.util.List;

public record DentistRequestDto(
        String firstName,
        String lastName,
        String phone,
        String email,
        String specialization
) {
}

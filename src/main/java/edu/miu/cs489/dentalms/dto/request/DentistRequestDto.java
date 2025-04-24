package edu.miu.cs489.dentalms.dto.request;

import edu.miu.cs489.dentalms.model.Appointment;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record DentistRequestDto(
        @NotBlank(message= "cannot be blank/empty/null")
        String firstName,
        @NotBlank(message= "cannot be blank/empty/null")
        String lastName,
        @NotBlank(message= "cannot be blank/empty/null")
        String phone,
        @NotBlank(message= "cannot be blank/empty/null")
        @Email(message = "invalid email format")
        String email,
        @NotBlank(message= "cannot be blank/empty/null")
        String password,
        @NotBlank(message= "cannot be blank/empty/null")
        String specialization
) {
}

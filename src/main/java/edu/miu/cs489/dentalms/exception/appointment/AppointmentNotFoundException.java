package edu.miu.cs489.dentalms.exception.appointment;

import edu.miu.cs489.dentalms.exception.NotFoundException;

public class AppointmentNotFoundException extends NotFoundException {
    public AppointmentNotFoundException(Long id) {
        super("appointment", id);
    }
}

package edu.miu.cs489.dentalms.exception.appointment;

public class AppointmentCannotUpdate extends RuntimeException {
    public AppointmentCannotUpdate() {
        super("Appointment cannot be updated");
    }
}

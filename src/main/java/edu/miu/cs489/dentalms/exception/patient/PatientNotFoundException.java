package edu.miu.cs489.dentalms.exception.patient;

public class PatientNotFoundException extends RuntimeException {
    public PatientNotFoundException(String message) {
        super(message);
    }
}

package edu.miu.cs489.dentalms.exception.patient;

import edu.miu.cs489.dentalms.exception.NotFoundException;

public class PatientNotFoundException extends NotFoundException {
    public PatientNotFoundException(Long  id) {
        super("Patient", id);
    }
}

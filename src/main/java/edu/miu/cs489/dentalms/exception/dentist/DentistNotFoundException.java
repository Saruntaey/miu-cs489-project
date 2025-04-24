package edu.miu.cs489.dentalms.exception.dentist;

import edu.miu.cs489.dentalms.exception.NotFoundException;

public class DentistNotFoundException extends NotFoundException {
    public DentistNotFoundException(Long  id) {
        super("Dentist", id);
    }
}

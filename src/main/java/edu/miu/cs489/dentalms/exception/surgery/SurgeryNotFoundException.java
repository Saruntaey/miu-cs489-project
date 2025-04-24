package edu.miu.cs489.dentalms.exception.surgery;

import edu.miu.cs489.dentalms.exception.NotFoundException;

public class SurgeryNotFoundException extends NotFoundException {
    public SurgeryNotFoundException(Long  id) {
        super("Surgery", id);
    }
}

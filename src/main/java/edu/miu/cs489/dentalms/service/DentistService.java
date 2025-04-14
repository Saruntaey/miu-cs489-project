package edu.miu.cs489.dentalms.service;

import edu.miu.cs489.dentalms.dto.request.DentistRequestDto;
import edu.miu.cs489.dentalms.dto.response.DentistResponseDto;

public interface DentistService {
    DentistResponseDto createDentist(DentistRequestDto dentist);
}

package edu.miu.cs489.dentalms.service;

import edu.miu.cs489.dentalms.dto.request.DentistRequestDto;
import edu.miu.cs489.dentalms.dto.response.DentistResponseDto;

import java.util.List;

public interface DentistService {
    DentistResponseDto createDentist(DentistRequestDto dentist);
    List<DentistResponseDto> getAllDentist();
}

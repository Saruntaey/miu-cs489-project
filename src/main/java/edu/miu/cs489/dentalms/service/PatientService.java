package edu.miu.cs489.dentalms.service;

import edu.miu.cs489.dentalms.dto.request.PatientRequestDto;
import edu.miu.cs489.dentalms.dto.response.PatientResponseDto;

public interface PatientService {
    PatientResponseDto createPatient(PatientRequestDto p);
}

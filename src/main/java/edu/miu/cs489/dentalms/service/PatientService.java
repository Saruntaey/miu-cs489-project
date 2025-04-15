package edu.miu.cs489.dentalms.service;

import edu.miu.cs489.dentalms.dto.request.PatientRequestDto;
import edu.miu.cs489.dentalms.dto.response.PatientResponseDto;

import java.util.List;

public interface PatientService {
    PatientResponseDto createPatient(PatientRequestDto p);
    List<PatientResponseDto> getAllPatients();
    List<PatientResponseDto> searchPatients(String searchString);
    PatientResponseDto getPatientById(Long id);
    PatientResponseDto updatePatient(Long id, PatientRequestDto p);
    void deletePatient(Long id);
}

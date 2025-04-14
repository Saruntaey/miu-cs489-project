package edu.miu.cs489.dentalms.service.impl;

import edu.miu.cs489.dentalms.dto.request.PatientRequestDto;
import edu.miu.cs489.dentalms.dto.response.PatientResponseDto;
import edu.miu.cs489.dentalms.mapper.PatientMapper;
import edu.miu.cs489.dentalms.model.Patient;
import edu.miu.cs489.dentalms.repo.PatientRepo;
import edu.miu.cs489.dentalms.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PatientRepo patientRepo;
    private final PatientMapper patientMapper;

    @Override
    public PatientResponseDto createPatient(PatientRequestDto patient) {
        Patient p = patientMapper.patientRequestDtoToPatient(patient);
        return patientMapper.patientToPatientResponseDto(patientRepo.save(p));
    }
}

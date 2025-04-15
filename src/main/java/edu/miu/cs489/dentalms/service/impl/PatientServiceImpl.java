package edu.miu.cs489.dentalms.service.impl;

import edu.miu.cs489.dentalms.dto.request.PatientRequestDto;
import edu.miu.cs489.dentalms.dto.response.PatientResponseDto;
import edu.miu.cs489.dentalms.exception.patient.PatientNotFoundException;
import edu.miu.cs489.dentalms.mapper.PatientMapper;
import edu.miu.cs489.dentalms.model.Patient;
import edu.miu.cs489.dentalms.repo.PatientRepo;
import edu.miu.cs489.dentalms.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public List<PatientResponseDto> getAllPatients() {
         return patientRepo.findAll(Sort.by(Sort.Direction.ASC, "lastName")).stream().map(patientMapper::patientToPatientResponseDto).collect(Collectors.toList());
    }

    @Override
    public List<PatientResponseDto> searchPatients(String searchString) {
        return patientRepo.findAllByFirstNameContainingIgnoreCase(searchString).stream().map(patientMapper::patientToPatientResponseDto).collect(Collectors.toList());
    }

    @Override
    public PatientResponseDto getPatientById(Long id) {
         Optional<Patient> patient = patientRepo.findById(id);
         if (patient.isEmpty()) {
             throw new PatientNotFoundException("not found user with id: " + id);
         }
         return patientMapper.patientToPatientResponseDto(patient.get());
    }

    @Override
    public PatientResponseDto updatePatient(Long id, PatientRequestDto p) {
        Optional<Patient> patient = patientRepo.findById(id);
        if (patient.isEmpty()) {
            throw new PatientNotFoundException("not found user with id: " + id);
        }
        Patient mp = patientMapper.patientRequestDtoToPatient(p);
        mp.setId(id);
        mp.getAddress().setId(patient.get().getAddress().getId());
        return patientMapper.patientToPatientResponseDto(patientRepo.save(mp));
    }

    @Override
    public void deletePatient(Long id) {
        Patient p = patientRepo.findById(id).orElseThrow(() -> new PatientNotFoundException("not found user with id: " + id));
        patientRepo.delete(p);
    }
}

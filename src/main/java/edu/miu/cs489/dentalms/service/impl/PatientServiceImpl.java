package edu.miu.cs489.dentalms.service.impl;

import edu.miu.cs489.dentalms.dto.request.PatientRequestDto;
import edu.miu.cs489.dentalms.dto.response.PatientResponseDto;
import edu.miu.cs489.dentalms.exception.patient.PatientNotFoundException;
import edu.miu.cs489.dentalms.exception.user.EmailDuplicateException;
import edu.miu.cs489.dentalms.mapper.PatientMapper;
import edu.miu.cs489.dentalms.model.Patient;
import edu.miu.cs489.dentalms.repo.PatientRepo;
import edu.miu.cs489.dentalms.service.PatientService;
import edu.miu.cs489.dentalms.user.Repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PatientRepo patientRepo;
    private final PatientMapper patientMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;

    @Override
    public PatientResponseDto createPatient(PatientRequestDto patient) {
        if (userRepo.findByEmail(patient.email()).isPresent()) {
            throw new EmailDuplicateException(patient.email());
        }
        Patient p = patientMapper.patientRequestDtoToPatient(patient);
        p.setPassword(passwordEncoder.encode(p.getPassword()));
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
             throw new PatientNotFoundException(id);
         }
         return patientMapper.patientToPatientResponseDto(patient.get());
    }

    @Override
    public PatientResponseDto updatePatient(Long id, PatientRequestDto p) {
        Optional<Patient> patient = patientRepo.findById(id);
        if (patient.isEmpty()) {
            throw new PatientNotFoundException(id);
        }
        Patient mp = patientMapper.patientRequestDtoToPatient(p);
        if (!mp.getEmail().equals(patient.get().getEmail())) {
            userRepo.findByEmail(mp.getEmail()).ifPresent(user -> {
                throw new EmailDuplicateException(mp.getEmail());
            });
        }
        mp.setId(id);
        mp.getAddress().setId(patient.get().getAddress().getId());
        return patientMapper.patientToPatientResponseDto(patientRepo.save(mp));
    }

    @Override
    public void deletePatient(Long id) {
        Patient p = patientRepo.findById(id).orElseThrow(() -> new PatientNotFoundException(id));
        patientRepo.delete(p);
    }
}

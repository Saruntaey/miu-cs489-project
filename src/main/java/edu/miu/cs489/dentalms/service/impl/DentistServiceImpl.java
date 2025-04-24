package edu.miu.cs489.dentalms.service.impl;

import edu.miu.cs489.dentalms.dto.request.DentistRequestDto;
import edu.miu.cs489.dentalms.dto.response.DentistResponseDto;
import edu.miu.cs489.dentalms.exception.user.EmailDuplicateException;
import edu.miu.cs489.dentalms.mapper.DentistMapper;
import edu.miu.cs489.dentalms.model.Dentist;
import edu.miu.cs489.dentalms.repo.DentistRepo;
import edu.miu.cs489.dentalms.service.DentistService;
import edu.miu.cs489.dentalms.user.Repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DentistServiceImpl implements DentistService {
    private final DentistRepo dentistRepo;
    private final DentistMapper dentistMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;

    @Override
    public DentistResponseDto createDentist(DentistRequestDto dentist) {
        if (userRepo.findByEmail(dentist.email()).isPresent()) {
            throw new EmailDuplicateException(dentist.email());
        }
        Dentist d = dentistMapper.dentistRequestDtoToDentist(dentist);
        d.setPassword(passwordEncoder.encode(d.getPassword()));
        return dentistMapper.dentistToDentistResponseDto(dentistRepo.save(d));
    }

    @Override
    public List<DentistResponseDto> getAllDentist() {
        return dentistRepo.findAll().stream().map(dentistMapper::dentistToDentistResponseDto).toList();
    }
}

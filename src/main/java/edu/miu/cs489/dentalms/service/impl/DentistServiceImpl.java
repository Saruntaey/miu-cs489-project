package edu.miu.cs489.dentalms.service.impl;

import edu.miu.cs489.dentalms.dto.request.DentistRequestDto;
import edu.miu.cs489.dentalms.dto.response.DentistResponseDto;
import edu.miu.cs489.dentalms.mapper.DentistMapper;
import edu.miu.cs489.dentalms.model.Dentist;
import edu.miu.cs489.dentalms.repo.DentistRepo;
import edu.miu.cs489.dentalms.service.DentistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DentistServiceImpl implements DentistService {
    private final DentistRepo dentistRepo;
    private final DentistMapper dentistMapper;

    @Override
    public DentistResponseDto createDentist(DentistRequestDto dentist) {
        Dentist d = dentistMapper.dentistRequestDtoToDentist(dentist);
        return dentistMapper.dentistToDentistResponseDto(dentistRepo.save(d));
    }
}

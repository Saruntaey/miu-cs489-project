package edu.miu.cs489.dentalms.service.impl;

import edu.miu.cs489.dentalms.dto.request.SurgeryRequestDto;
import edu.miu.cs489.dentalms.dto.response.SurgeryResponseDto;
import edu.miu.cs489.dentalms.mapper.SurgeryMapper;
import edu.miu.cs489.dentalms.model.Surgery;
import edu.miu.cs489.dentalms.repo.SurgeryRepo;
import edu.miu.cs489.dentalms.service.SurgeryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SurgeryServiceImpl implements SurgeryService {
    private final SurgeryRepo surgeryRepo;
    private final SurgeryMapper surgeryMapper;

    @Override
    public SurgeryResponseDto createSurgery(SurgeryRequestDto surgery) {
        Surgery s = surgeryMapper.surgeryRequestDtoToSurgery(surgery);
        return surgeryMapper.surgeryToSurgeryResponseDto(surgeryRepo.save(s));
    }
}

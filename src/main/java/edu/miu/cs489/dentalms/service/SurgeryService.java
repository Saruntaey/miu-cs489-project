package edu.miu.cs489.dentalms.service;

import edu.miu.cs489.dentalms.dto.request.SurgeryRequestDto;
import edu.miu.cs489.dentalms.dto.response.SurgeryResponseDto;

public interface SurgeryService {
    SurgeryResponseDto createSurgery(SurgeryRequestDto surgery);
}

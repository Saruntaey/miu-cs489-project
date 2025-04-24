package edu.miu.cs489.dentalms.controller;

import edu.miu.cs489.dentalms.dto.response.SurgeryResponseDto;
import edu.miu.cs489.dentalms.service.SurgeryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/surgeries")
@RequiredArgsConstructor
public class Surgery {
    private final SurgeryService surgeryService;

    @GetMapping
    public List<SurgeryResponseDto> getAllSurgery() {
        return surgeryService.getAllSurgery();
    }
}

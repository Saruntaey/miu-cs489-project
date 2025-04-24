package edu.miu.cs489.dentalms.controller;

import edu.miu.cs489.dentalms.dto.request.DentistRequestDto;
import edu.miu.cs489.dentalms.dto.response.DentistResponseDto;
import edu.miu.cs489.dentalms.service.DentistService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dentists")
@RequiredArgsConstructor
public class Dentist {
    private final DentistService dentistService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('OFFICE_MANAGER')")
    DentistResponseDto createDentist(@RequestBody @Valid DentistRequestDto d) {
        return dentistService.createDentist(d);
    }

    @GetMapping
    public List<DentistResponseDto> getAllDentist() {
        return dentistService.getAllDentist();
    }
}

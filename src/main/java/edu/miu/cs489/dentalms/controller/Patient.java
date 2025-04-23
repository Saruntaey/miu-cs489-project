package edu.miu.cs489.dentalms.controller;

import edu.miu.cs489.dentalms.dto.request.PatientRequestDto;
import edu.miu.cs489.dentalms.dto.response.PatientResponseDto;
import edu.miu.cs489.dentalms.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patients")
@RequiredArgsConstructor
public class Patient {
    private final PatientService patientService;

    @GetMapping
    List<PatientResponseDto> getPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    PatientResponseDto getPatientById(@PathVariable("id") Long id) {
        return patientService.getPatientById(id);
    }

    @PostMapping
    ResponseEntity<PatientResponseDto> createPatient(@RequestBody @Valid PatientRequestDto p) {
        PatientResponseDto created =  patientService.createPatient(p);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);

    }

    @PutMapping("/{id}")
    PatientResponseDto updatePatient(@PathVariable("id") Long id, @RequestBody PatientRequestDto p) {
        return  patientService.updatePatient(id, p);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Object> deletePatient(@PathVariable("id") Long id) {
         patientService.deletePatient(id);
         return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/{searchString}")
    List<PatientResponseDto> searchPatient(@PathVariable("searchString") String searchString) {
        return patientService.searchPatients(searchString);
    }
}

package edu.miu.cs489.dentalms.repo;

import edu.miu.cs489.dentalms.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepo extends JpaRepository<Patient, Long> {
    List<Patient> findAllByFirstNameContainingIgnoreCase(String firstName);
}

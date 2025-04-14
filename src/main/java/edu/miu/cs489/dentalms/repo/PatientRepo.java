package edu.miu.cs489.dentalms.repo;

import edu.miu.cs489.dentalms.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepo extends JpaRepository<Patient, Long> {
}

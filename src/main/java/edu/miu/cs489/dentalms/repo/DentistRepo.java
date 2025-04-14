package edu.miu.cs489.dentalms.repo;

import edu.miu.cs489.dentalms.model.Dentist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DentistRepo extends JpaRepository<Dentist, Long> {
}

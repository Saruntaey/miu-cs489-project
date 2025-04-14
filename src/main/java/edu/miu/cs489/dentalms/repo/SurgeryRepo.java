package edu.miu.cs489.dentalms.repo;

import edu.miu.cs489.dentalms.model.Surgery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurgeryRepo extends JpaRepository<Surgery, Long> {
}

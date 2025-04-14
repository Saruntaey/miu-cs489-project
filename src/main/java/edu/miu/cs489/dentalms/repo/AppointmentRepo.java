package edu.miu.cs489.dentalms.repo;

import edu.miu.cs489.dentalms.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepo extends JpaRepository<Appointment, Long> {
}

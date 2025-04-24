package edu.miu.cs489.dentalms.repo;

import edu.miu.cs489.dentalms.model.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AppointmentRepo extends JpaRepository<Appointment, Long> {
   Page<Appointment> findAllByPatientId(Long patientId, Pageable pageable);
   Page<Appointment> findAllByDentistId(Long dentistId, Pageable pageable);
}

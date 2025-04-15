package edu.miu.cs489.dentalms.repo;

import edu.miu.cs489.dentalms.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<Address, Long> {
}

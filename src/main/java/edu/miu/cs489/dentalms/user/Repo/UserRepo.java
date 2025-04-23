package edu.miu.cs489.dentalms.user.Repo;

import edu.miu.cs489.dentalms.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}

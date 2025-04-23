package edu.miu.cs489.dentalms.model;

import edu.miu.cs489.dentalms.user.Role;
import edu.miu.cs489.dentalms.user.User;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
public class OfficeManager extends User {
    public OfficeManager() {
        this.role = Role.OFFICE_MANAGER;
    }

    public OfficeManager(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = Role.OFFICE_MANAGER;
    }
}

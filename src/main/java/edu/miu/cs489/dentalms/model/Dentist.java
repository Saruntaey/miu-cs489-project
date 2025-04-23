package edu.miu.cs489.dentalms.model;

import edu.miu.cs489.dentalms.user.Role;
import edu.miu.cs489.dentalms.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
public class Dentist  extends User {
    public Dentist() {
        this.role = Role.DENTIST;
    }
    @Column(length = 25)
    private String phone;
    private String specialization;
    @OneToMany(mappedBy = "dentist")
    private List<Appointment> appointments;
}

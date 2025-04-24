package edu.miu.cs489.dentalms.model;

import edu.miu.cs489.dentalms.user.Role;
import edu.miu.cs489.dentalms.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Patient extends User {
    public Patient() {
        this.role = Role.PATIENT;
    }

    @Column(length = 25)
    private String phone;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="address_id")
    private Address address;

    @OneToMany(mappedBy="patient")
    private List<Appointment> appointments;
}

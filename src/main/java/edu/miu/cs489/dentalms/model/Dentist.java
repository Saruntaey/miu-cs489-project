package edu.miu.cs489.dentalms.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Dentist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 50)
    private String firstName;
    @Column(length = 50)
    private String lastName;
    @Column(length = 25)
    private String phone;
    @Column(length = 50)
    private String email;
    private String specialization;
    @OneToMany(mappedBy = "dentist")
    private List<Appointment> appointments;
}

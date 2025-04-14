package edu.miu.cs489.dentalms.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length=50)
    private String street;
    @Column(length=50)
    private String city;
    @Column(length=50)
    private String state;
    @Column(length=10)
    private String zip;
}

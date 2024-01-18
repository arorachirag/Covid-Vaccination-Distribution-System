package com.example.Demo_JPA.Model;


import com.example.Demo_JPA.enums.VaccinationPreference;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    String name;
    String gender;
    int doseCount;

    String vaccinationPreference;
    @Column(unique = true)
    String aadharNo;
    @Column(unique = true)
    long mobileNo;
    @Column(unique = true)
    String email;
    String password;
}
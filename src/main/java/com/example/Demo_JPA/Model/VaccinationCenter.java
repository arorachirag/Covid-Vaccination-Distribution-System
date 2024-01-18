package com.example.Demo_JPA.Model;


import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class VaccinationCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    String name;
    String type;

    int covaxinCount;
    int sputnikCount;
    int coviShieldCount;

    int doctorCount;
    int patientCount;

    @OneToMany(mappedBy = "vaccinationCenter")
    List<Doctor> doctorList;

    public VaccinationCenter() {
    }

    public VaccinationCenter(UUID id, String name, String type, int covaxinCount, int sputnikCount, int coviShieldCount, int doctorCount, int patientCount,List<Doctor> doctorList) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.covaxinCount = covaxinCount;
        this.sputnikCount = sputnikCount;
        this.coviShieldCount = coviShieldCount;
        this.doctorCount = doctorCount;
        this.patientCount = patientCount;
        this.doctorList = doctorList;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCovaxinCount() {
        return covaxinCount;
    }

    public void setCovaxinCount(int covaxinCount) {
        this.covaxinCount = covaxinCount;
    }

    public int getSputnikCount() {
        return sputnikCount;
    }

    public void setSputnikCount(int sputnikCount) {
        this.sputnikCount = sputnikCount;
    }

    public int getCoviShieldCount() {
        return coviShieldCount;
    }

    public void setCoviShieldCount(int coviShieldCount) {
        this.coviShieldCount = coviShieldCount;
    }

    public int getDoctorCount() {
        return doctorCount;
    }

    public void setDoctorCount(int doctorCount) {
        this.doctorCount = doctorCount;
    }

    public int getPatientCount() {
        return patientCount;
    }

    public void setPatientCount(int patientCount) {
        this.patientCount = patientCount;
    }
}

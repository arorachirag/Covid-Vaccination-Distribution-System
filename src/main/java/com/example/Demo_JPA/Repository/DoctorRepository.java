package com.example.Demo_JPA.Repository;

import com.example.Demo_JPA.Model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface DoctorRepository extends JpaRepository<Doctor, UUID> {



}

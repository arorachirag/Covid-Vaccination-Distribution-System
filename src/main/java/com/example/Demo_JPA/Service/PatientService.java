package com.example.Demo_JPA.Service;


import com.example.Demo_JPA.Model.Patient;
import com.example.Demo_JPA.Repository.PatientRepository;
import com.example.Demo_JPA.dto.request.PatientLoginDTO;
import com.example.Demo_JPA.dto.request.PatientSignUpDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    @Autowired
    PatientRepository patientRepository;

    public Patient signUp(PatientSignUpDTO patientSignUpDTO){
        Patient patient = new Patient();
        patient.setName(patientSignUpDTO.getName());
        patient.setAadharNo(patientSignUpDTO.getAadharNo());
        patient.setEmail(patientSignUpDTO.getEmail());
        patient.setPassword(patientSignUpDTO.getPassword());
        patient.setGender(patientSignUpDTO.getGender());
        patient.setMobileNo(patientSignUpDTO.getMobileNo());
        patient.setVaccinationPreference(patientSignUpDTO.getVaccinationPreference().toString());
        patientRepository.save(patient);
        return patient;
    }

    public Patient login(PatientLoginDTO patientLoginDTO){
        Patient patient = new Patient();
        patient.setEmail(patientLoginDTO.getEmail());
        patient.setPassword(patientLoginDTO.getPassword());
        patientRepository.save(patient);
        return patient;
    }
}

package com.example.Demo_JPA.Controller;


import com.example.Demo_JPA.Model.Patient;
import com.example.Demo_JPA.Service.PatientService;
import com.example.Demo_JPA.dto.request.PatientLoginDTO;
import com.example.Demo_JPA.dto.request.PatientSignUpDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    PatientService patientService;

    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody PatientSignUpDTO patientSignUpDTO){
        Patient patient = patientService.signUp(patientSignUpDTO);
        return new ResponseEntity<>(patient, HttpStatus.CREATED);
    }

    public ResponseEntity login(@RequestBody PatientLoginDTO patientLoginDTO){
        Patient patient = patientService.login(patientLoginDTO);
        return new ResponseEntity<>(patient,HttpStatus.CREATED);
    }
}

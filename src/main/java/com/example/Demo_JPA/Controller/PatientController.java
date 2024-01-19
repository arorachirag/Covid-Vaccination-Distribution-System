package com.example.Demo_JPA.Controller;


import com.example.Demo_JPA.Model.Patient;
import com.example.Demo_JPA.Model.VaccinationCenter;
import com.example.Demo_JPA.Service.PatientService;
import com.example.Demo_JPA.dto.request.CreateAppointmentDTO;
import com.example.Demo_JPA.dto.request.PatientLoginDTO;
import com.example.Demo_JPA.dto.request.PatientSignUpDTO;
import com.example.Demo_JPA.dto.response.AppointmentDTO;
import com.example.Demo_JPA.dto.response.GeneralMessageDTO;
import com.example.Demo_JPA.enums.VaccinationCenterPreference;
import com.example.Demo_JPA.exceptions.PatientDoesNotExists;
import com.example.Demo_JPA.exceptions.WrongCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody PatientLoginDTO patientLoginDTO){
        try {
            Patient patient = patientService.login(patientLoginDTO);
            return new ResponseEntity<>(patient, HttpStatus.OK);
        } catch (PatientDoesNotExists patientDoesNotExists){
            return new ResponseEntity(new GeneralMessageDTO(patientDoesNotExists.getMessage()),HttpStatus.NOT_FOUND);
        } catch (WrongCredentials wrongCredentials){
            return new ResponseEntity(new GeneralMessageDTO(wrongCredentials.getMessage()),HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/createappointment")
    public ResponseEntity create(@RequestParam String email, @RequestParam VaccinationCenterPreference vaccinationCenterPreference){
        AppointmentDTO appointmentDTO =  patientService.createAppointment(email,vaccinationCenterPreference.toString());

        return new ResponseEntity(appointmentDTO,HttpStatus.OK);
    }


}

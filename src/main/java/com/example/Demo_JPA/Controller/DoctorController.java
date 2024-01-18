package com.example.Demo_JPA.Controller;


import com.example.Demo_JPA.Model.Doctor;
import com.example.Demo_JPA.Service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @PostMapping("/register")
    public String register(@RequestBody Doctor doctor){
        Doctor doctor1 = doctorService.registerDoctor(doctor);
        return "Doctor added";
    }
}

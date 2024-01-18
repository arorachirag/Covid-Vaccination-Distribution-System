package com.example.Demo_JPA.Controller;


import com.example.Demo_JPA.Model.VaccinationCenter;
import com.example.Demo_JPA.Service.VaccinationCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VaccinationCenterController {

    @Autowired
    VaccinationCenterService vaccinationCenterService;

    @PostMapping("/register")
    public VaccinationCenter register(@RequestBody VaccinationCenter vaccinationCenter){
        return vaccinationCenterService.registerVaccinationCenter(vaccinationCenter);

    }
}

package com.example.Demo_JPA.Service;

import com.example.Demo_JPA.Model.Doctor;
import com.example.Demo_JPA.Model.VaccinationCenter;
import com.example.Demo_JPA.Repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    VaccinationCenterService vaccinationCenterService;

    public Doctor registerDoctor(Doctor doctor){
        doctorRepository.save(doctor);
        List<VaccinationCenter> vaccinationCenterList = vaccinationCenterService.getMinimumDoctorCount();
        VaccinationCenter vaccinationCenter = vaccinationCenterList.get(0);
        doctor.setVaccinationCenter(vaccinationCenter);
        vaccinationCenterService.updateDocCountByOne(vaccinationCenter);
        return doctor;
    }
}

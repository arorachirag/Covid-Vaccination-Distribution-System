package com.example.Demo_JPA.Service;


import com.example.Demo_JPA.Model.VaccinationCenter;
import com.example.Demo_JPA.Repository.VaccinationCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class VaccinationCenterService {

    @Autowired
    VaccinationCenterRepository vaccinationCenterRepository;

    public VaccinationCenter registerVaccinationCenter(VaccinationCenter vaccinationCenter){

        return vaccinationCenterRepository.save(vaccinationCenter);

    }

    public List<VaccinationCenter> getMinimumDoctorCount(){
        List<VaccinationCenter> vaccinationCenters = vaccinationCenterRepository.getMinimumDoctorVaccinationCenter();
        return vaccinationCenters;
    }

    public void updateDocCountByOne(VaccinationCenter vaccinationCenter){
        UUID id = vaccinationCenter.getId();
        int docCount = vaccinationCenter.getDoctorCount() + 1;
        vaccinationCenterRepository.updateDocCountByOne(id,docCount);
    }
}

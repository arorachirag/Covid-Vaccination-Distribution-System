package com.example.Demo_JPA.Service;


import com.example.Demo_JPA.Model.Doctor;
import com.example.Demo_JPA.Model.Patient;
import com.example.Demo_JPA.Model.VaccinationCenter;
import com.example.Demo_JPA.Repository.PatientRepository;
import com.example.Demo_JPA.Repository.VaccinationCenterRepository;
import com.example.Demo_JPA.dto.request.CreateAppointmentDTO;
import com.example.Demo_JPA.dto.request.PatientLoginDTO;
import com.example.Demo_JPA.dto.request.PatientSignUpDTO;
import com.example.Demo_JPA.dto.response.AppointmentDTO;
import com.example.Demo_JPA.exceptions.PatientDoesNotExists;
import com.example.Demo_JPA.exceptions.WrongCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PatientService {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    VaccinationCenterService vaccinationCenterService;

    @Autowired
    DoctorService doctorService;

    @Autowired
    MailService mailService;

    public Patient signUp(PatientSignUpDTO patientSignupDTO){
        Patient patient = new Patient();
        patient.setName(patientSignupDTO.getName());
        patient.setGender(patientSignupDTO.getGender());
        patient.setEmail(patientSignupDTO.getEmail());

        patient.setAadharNo(patientSignupDTO.getAadharNo());
        patient.setPassword(patientSignupDTO.getPassword());
        patient.setMobileNo(patientSignupDTO.getMobileNo());
        patient.setVaccinationPreference(patientSignupDTO.getVaccinationPreference().toString());
        patientRepository.save(patient);
        return patient;
    }


    public Patient login(PatientLoginDTO patientLoginDTO){
        Patient patient = patientRepository.getPatientsByEmail(patientLoginDTO.getEmail());
        if(patient == null){
            throw new PatientDoesNotExists("Patient email Id is not registered in our portal.");
        }
        if(!patient.getPassword().equals(patientLoginDTO.getPassword())){
            throw new WrongCredentials("Patient Entered Wrong Password.");
        }
        return patient;
    }

    public AppointmentDTO createAppointment(String email, String vaccinationCenterPreference){
        // 1. get patient by email
        Patient p = patientRepository.getPatientsByEmail(email);
        // 2. Identify patient vaccination prefrence
        String vPrefrence = p.getVaccinationPreference();
        List<VaccinationCenter> vcList = vaccinationCenterService.getMinimumVCOnTheBasisOfTypeAndPrefrence(vaccinationCenterPreference, vPrefrence);
        // 3. Assigning 0th index vaccination center to patient
        VaccinationCenter patientsVC = vcList.get(0);
        // 4. Assign doctor who is handeling minimum number of patients to the current patient
        List<Doctor> docList = doctorService.getMinimumDoctorOnTheBasisOfVC(patientsVC.getId());
        // 5. Take out minimum doctor
        Doctor patientDoctor = docList.get(0);
        // HomeWork
        // VaccinationCenter -> patients count + 1
        // Doctor -> patientCount + 1
        // Doctor -> List -> add patient -> Database -> Insert docId, pid into docvs patient table
        // return response body -> patient details, patientc vc details, doctor details
        updateDoseCountByOne(p);
        vaccinationCenterService.updatePatientCountByOne(patientsVC);
        doctorService.updatePatientCountByOne(patientDoctor);
        //patientDoctor.getPatientCount().add(p);
        //doctorService.addPatientVsDoctor(p.getId(), patientDoctor.getId());
        AppointmentDTO appointmentDTO = new AppointmentDTO();
        appointmentDTO.setPatient(p);
        appointmentDTO.setDoseNumber(p.getDoseCount() + 1);
        appointmentDTO.setDocID(patientDoctor.getId());
        appointmentDTO.setDocName(patientDoctor.getName());
        appointmentDTO.setVcID(patientsVC.getId());
        appointmentDTO.setVaccinationCenterName(patientsVC.getName());

        // Send mail
        String to = p.getEmail();
        String sub = String.format("Your appointment is confirmed ",p.getName());
        String text = String.format("Hii %s," +
                        "\n Your appointment got created. Below are your appointment details :" +
                        "\n1. Dose Count : %d" +
                        "\n2. Doctor Name : %s" +
                        "\n3. Vaccination Center Name  : %s",

                p.getName(),
                p.getDoseCount(),
                patientDoctor.getName(),
                patientsVC.getName()
                );
        mailService.generateMail(to,sub,text);
        return appointmentDTO;
    }

    public void updateDoseCountByOne(Patient patient){
        UUID id = patient.getId();
        int doseCount = patient.getDoseCount() + 1;
        patientRepository.updateDoseCountByOne(id, doseCount);
    }

}

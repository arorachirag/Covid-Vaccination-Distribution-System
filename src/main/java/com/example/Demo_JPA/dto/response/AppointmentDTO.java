package com.example.Demo_JPA.dto.response;

import com.example.Demo_JPA.Model.Doctor;
import com.example.Demo_JPA.Model.Patient;
import com.example.Demo_JPA.Model.VaccinationCenter;
import lombok.*;

import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AppointmentDTO {
    int doseNumber;
    Patient patient;
    UUID docID;
    String docName;
    UUID vcID;
    String vaccinationCenterName;
}

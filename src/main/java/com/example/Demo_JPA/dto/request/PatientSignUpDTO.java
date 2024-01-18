package com.example.Demo_JPA.dto.request;


import com.example.Demo_JPA.enums.VaccinationPreference;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PatientSignUpDTO {
    String name;
    String gender;
    String email;
    VaccinationPreference vaccinationPreference;
    String aadharNo;
    String password;
    long mobileNo;


}

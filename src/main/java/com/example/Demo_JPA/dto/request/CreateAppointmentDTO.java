package com.example.Demo_JPA.dto.request;

import com.example.Demo_JPA.enums.VaccinationCenterPreference;
import com.example.Demo_JPA.enums.VaccinationPreference;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CreateAppointmentDTO {
    UUID id;

    VaccinationCenterPreference vaccinationCenterPreference;
}

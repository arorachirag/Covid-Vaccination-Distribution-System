package com.example.Demo_JPA.dto.request;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PatientLoginDTO {
    String email;
    String password;
}

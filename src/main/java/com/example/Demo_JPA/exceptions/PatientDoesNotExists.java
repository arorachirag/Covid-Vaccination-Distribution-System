package com.example.Demo_JPA.exceptions;

public class PatientDoesNotExists extends RuntimeException{
    public PatientDoesNotExists(String message){
        super(message);
    }
}

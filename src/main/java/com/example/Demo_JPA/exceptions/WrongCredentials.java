package com.example.Demo_JPA.exceptions;

public class WrongCredentials extends RuntimeException {
    public WrongCredentials(String msg){
        super(msg);
    }
}

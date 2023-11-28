package com.example.ParcialSabado28.excepctions;

public class EmployeeNotFounException extends RuntimeException {
    public EmployeeNotFounException(String message) {
        super(message);
    }
}

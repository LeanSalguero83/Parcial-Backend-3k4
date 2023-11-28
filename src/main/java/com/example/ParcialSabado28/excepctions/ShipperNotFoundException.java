package com.example.ParcialSabado28.excepctions;

public class ShipperNotFoundException extends RuntimeException {
    public ShipperNotFoundException(String message) {
        super(message);
    }
}

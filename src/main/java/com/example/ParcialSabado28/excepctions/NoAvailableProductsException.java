package com.example.ParcialSabado28.excepctions;

public class NoAvailableProductsException extends RuntimeException {
    public NoAvailableProductsException(String message) {
        super(message);
    }
}


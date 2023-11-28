package com.example.ParcialSabado28.excepctions;

public class NegativeStockException extends RuntimeException {
    public NegativeStockException(String message) {
        super(message);
    }
}
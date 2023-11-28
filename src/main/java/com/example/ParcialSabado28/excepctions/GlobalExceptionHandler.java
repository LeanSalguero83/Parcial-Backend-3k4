package com.example.ParcialSabado28.excepctions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(SupplierNotFoundException.class)
    public ResponseEntity<String> handleSupplierNotFoundException(SupplierNotFoundException ex) {
        // Esto devolverá un estado HTTP 404 con el mensaje de la excepción
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<String> handleCategoryNotFoundException(CategoryNotFoundException ex) {
        // Esto devolverá un estado HTTP 404 con el mensaje de la excepción
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<String> handleCustomerNotFoundException(CustomerNotFoundException ex) {
        // Esto devolverá un estado HTTP 404 con el mensaje de la excepción
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(EmployeeNotFounException.class)
    public ResponseEntity<String> handleEmployeeNotFoundException(EmployeeNotFounException ex) {
        // Esto devolverá un estado HTTP 404 con el mensaje de la excepción
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(ShipperNotFoundException.class)
    public ResponseEntity<String> handleShipperNotFoundException(ShipperNotFoundException ex) {
        // Esto devolverá un estado HTTP 404 con el mensaje de la excepción
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        // Esto capturará cualquier otra excepción no manejada específicamente
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR) // Estado HTTP 500
                .body("Ocurrió un error en el servidor: " + ex.getMessage());
    }

    @ExceptionHandler(NegativeStockException.class)
    public ResponseEntity<String> handleNegativeStockException(NegativeStockException ex) {
        // Esto devolverá un estado HTTP 400 (Bad Request) con el mensaje de la excepción
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(NoAvailableProductsException.class)
    public ResponseEntity<String> handleNoAvailableProductsException(NoAvailableProductsException ex) {
        // Esto devolverá un estado HTTP 204 con el mensaje de la excepción
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ex.getMessage());
    }



}

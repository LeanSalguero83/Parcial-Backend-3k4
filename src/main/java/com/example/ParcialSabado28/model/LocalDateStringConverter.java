package com.example.ParcialSabado28.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Converter(autoApply = true)
public class LocalDateStringConverter  implements AttributeConverter<LocalDate, String> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public String convertToDatabaseColumn(LocalDate localDate) {
        return localDate != null ? localDate.format(formatter) : null;
    }

    @Override
    public LocalDate convertToEntityAttribute(String s) {
        return s != null ? LocalDate.parse(s, formatter) : null;
    }
}
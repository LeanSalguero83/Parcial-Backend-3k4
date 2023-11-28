package com.example.ParcialSabado28.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EmployeeTerritoryId implements Serializable {

    private Integer employeeId;
    private String territoryId;

    public EmployeeTerritoryId() {
    }

    public EmployeeTerritoryId(Integer employeeId, String territoryId) {
        this.employeeId = employeeId;
        this.territoryId = territoryId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeTerritoryId that = (EmployeeTerritoryId) o;
        return Objects.equals(employeeId, that.employeeId) &&
                Objects.equals(territoryId, that.territoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, territoryId);
    }
}

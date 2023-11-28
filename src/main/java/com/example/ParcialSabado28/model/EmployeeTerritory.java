package com.example.ParcialSabado28.model;

import jakarta.persistence.*;

@Entity
@Table(name = EmployeeTerritory.TABLE_NAME)
public class EmployeeTerritory {
    public static final String TABLE_NAME = "EmployeeTerritories";
    @EmbeddedId
    private EmployeeTerritoryId id;

    @ManyToOne
    @JoinColumn(name = "EmployeeID", referencedColumnName = "EmployeeID", insertable = false, updatable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "TerritoryID", referencedColumnName = "TerritoryID", insertable = false, updatable = false)
    private Territory territory;

}
//gracias a insertable = false, updatable = false)
//    EmployeeTerritoryId id = new EmployeeTerritoryId(employee.getEmployeeId(), territory.getTerritoryId());
//    EmployeeTerritory et = new EmployeeTerritory();
//et.setId(id);
//        employeeTerritoryRepository.save(et);


//Para aclarar un poco más, cuando tienes insertable = false, updatable = false, no podrás hacer algo como esto:
//Employee employee = new Employee();
//    //... asignar otros valores
//    Territory territory = new Territory();
////... asignar otros valores
//
//    EmployeeTerritory employeeTerritory = new EmployeeTerritory();
//employeeTerritory.setEmployee(employee);
//        employeeTerritory.setTerritory(territory);
//
//        employeeTerritoryRepository.save(employeeTerritory); // Esto no actualizará EmployeeID ni TerritoryID en la tabla EmployeeTerritories

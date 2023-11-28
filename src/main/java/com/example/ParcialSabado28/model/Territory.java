package com.example.ParcialSabado28.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.*;
import lombok.experimental.FieldDefaults;
import jakarta.persistence.*;

import java.util.Set;


@Entity
@Table(name = Territory.TABLE_NAME)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Territory {

    public static final String TABLE_NAME = "Territories";

    @Id
    @Column(name = "TerritoryID")
    String territoryId;

    @Column(name = "TerritoryDescription", nullable = false)
    String territoryDescription;


    @ManyToOne
    @JoinColumn(name = "RegionID")
    Region region;


//    @OneToMany(mappedBy = "territory")
//    private Set<EmployeeTerritory> employeeTerritories;


}
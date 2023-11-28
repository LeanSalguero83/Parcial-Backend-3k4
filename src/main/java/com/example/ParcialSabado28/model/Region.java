package com.example.ParcialSabado28.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Table(name = Region.TABLE_NAME)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Region {

    public static final String TABLE_NAME = "Regions";

    @Id
    @Column(name = "RegionID")
    Integer regionId;

    @Column(name = "RegionDescription", nullable = false)
    String regionDescription;

//    @OneToMany(mappedBy = "region")
//    Set<Territory> territories;

}
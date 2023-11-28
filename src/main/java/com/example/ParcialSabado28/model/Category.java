package com.example.ParcialSabado28.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = Category.TABLE_NAME)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Category {
    public static final String TABLE_NAME = "Categories";
    @Id
    @Column(name = "CategoryID")
    Integer categoryId;
    @Column(name = "CategoryName")
    String categoryName;
    @Column(name = "Description")
    String description;
    @Column(name = "Picture")
    byte[] picture;
    @OneToMany(mappedBy = "category")
    List<Product> products;

}

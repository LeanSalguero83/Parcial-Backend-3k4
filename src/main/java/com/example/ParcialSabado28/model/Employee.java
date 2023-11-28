    package com.example.ParcialSabado28.model;

    import jakarta.persistence.*;
    import lombok.*;
    import lombok.experimental.FieldDefaults;

    import java.time.LocalDate;
    import java.util.List;
    import java.util.Set;

    @Entity
    @Table(name = Employee.TABLE_NAME)
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public class Employee {
        public static final String TABLE_NAME = "Employees";
        @Id
        @Column(name = "EmployeeID")
        Integer employeeId;
        @Column(name = "LastName", nullable = false)
        String lastName;
        @Column(name = "FirstName", nullable = false)
        String firstName;
        @Column(name = "Title")
        String title;
        @Column(name = "TitleOfCourtesy")
        String titleOfCourtesy;
        @Column(name = "BirthDate")
        String birthDate;
        @Column(name = "HireDate")
        String hireDate;
        @Column(name = "Address")
        String address;
        @Column(name = "City")
        String city;
        @Column(name = "Region")
        String region;
        @Column(name = "PostalCode")
        String postalCode;
        @Column(name = "Country")
        String country;
        @Column(name = "HomePhone")
        String homePhone;
        @Column(name = "Extension")
        String extension;
        @Column(name = "Photo")
        byte[] photo;
        @Column(name = "Notes")
        String notes;
        @Column(name = "PhotoPath")
        String photoPath;
        @ManyToOne
        @JoinColumn(name = "ReportsTo")
        Employee manager;
        @OneToMany(mappedBy = "employee")
        List<Order> shipper;
    }

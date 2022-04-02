package com.andresvg8.employeeapi.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee {
    @Id
    @Getter @Setter private Long id;

    @Getter @Setter private String employee_name;

    @Getter @Setter private Long employee_salary;

    @Getter @Setter private Long employee_age;

    @Getter @Setter private String profile_image;
}

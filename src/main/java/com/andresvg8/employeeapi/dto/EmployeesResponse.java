package com.andresvg8.employeeapi.dto;

import lombok.Getter;
import lombok.Setter;

public class EmployeesResponse {
    @Getter
    @Setter
    private String status;

    @Getter @Setter private String message;

    @Getter @Setter private EmployeeDto[] data;
}

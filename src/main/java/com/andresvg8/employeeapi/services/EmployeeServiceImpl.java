package com.andresvg8.employeeapi.services;

import com.andresvg8.employeeapi.dto.EmployeeDto;
import com.andresvg8.employeeapi.entities.Employee;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeDtoService{
    @Override
    public Long calculateAnnualSalary(EmployeeDto employee) {
        Long annualSalary = null;
        if(employee!=null && employee.getEmployee_salary()!=null){
            annualSalary = employee.getEmployee_salary() * 12;
        }
        return annualSalary;
    }
}

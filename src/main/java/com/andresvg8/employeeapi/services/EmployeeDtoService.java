/**
 * 
 */
package com.andresvg8.employeeapi.services;

import com.andresvg8.employeeapi.dto.EmployeeDto;
import com.andresvg8.employeeapi.entities.Employee;

/**
 * @author ANDRES-1
 *
 */
public interface EmployeeDtoService {
    public Long calculateAnnualSalary(EmployeeDto employee);
}

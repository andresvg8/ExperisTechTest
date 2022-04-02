/**
 * 
 */
package com.andresvg8.employeeapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author ANDRES-1
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeDto {
	@Getter @Setter private Long id;
	@Getter @Setter private String employee_name;
	@Getter @Setter private Long employee_salary;
	@Getter @Setter private Long employee_age;
	@Getter @Setter private String profile_image;
}

/**
 * 
 */
package com.andresvg8.employeeapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author ANDRES-1
 *
 */
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponse {
	@Getter @Setter private String status;
	
	@Getter @Setter private String message;
	
	@Getter @Setter private EmployeeDto data;
}

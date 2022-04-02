/**
 * 
 */
package com.andresvg8.employeeapi.controllers;

import com.andresvg8.employeeapi.dto.EmployeesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.andresvg8.employeeapi.dto.EmployeeDto;
import com.andresvg8.employeeapi.dto.EmployeeResponse;

/**
 * @author ANDRES-1
 *
 */
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	@Autowired
	private WebClient.Builder webClient;

	private final String endPoint = "http://dummy.restapiexample.com/api/v1";
	
	@GetMapping("/{employeeId}")
	public ResponseEntity<?> getEmployee(@PathVariable Long employeeId){
		try{
			EmployeeResponse employeeResponse = webClient.build().get()
				.uri(endPoint+"/employee/"+employeeId)
				.retrieve()
				.bodyToMono(EmployeeResponse.class)
				.block();
			EmployeeDto employeeDto = employeeResponse.getData();
			return ResponseEntity.ok(employeeDto);
		}
		catch(Exception getEmployeeDtoException) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("\tNo se puede recuperar employeeDto: " + getEmployeeDtoException);
		}
	}
	
	@GetMapping(value={"/", ""})
	public ResponseEntity<?> getEmployees(){
		try{
			EmployeesResponse employeesResponse = webClient.build().get()
				.uri(endPoint+"/employees")
				.retrieve()
				.bodyToMono(EmployeesResponse.class)
				.block();
			EmployeeDto[] employeeDto = employeesResponse.getData();
			return ResponseEntity.ok(employeeDto);
		}
		catch(Exception getEmployeesDtoException) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("\tNo se puede recuperar employeesDto: " + getEmployeesDtoException);
		}
	}
}

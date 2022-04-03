/**
 * 
 */
package com.andresvg8.employeeapi.controllers;

import com.andresvg8.employeeapi.dto.EmployeesResponse;
import com.andresvg8.employeeapi.services.EmployeeDtoService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.andresvg8.employeeapi.dto.EmployeeDto;
import com.andresvg8.employeeapi.dto.EmployeeResponse;
import reactor.core.publisher.Mono;

/**
 * @author ANDRES-1
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	@Autowired
	private EmployeeDtoService employeeDtoService;

	@Autowired
	private WebClient.Builder webClient;

	private final String endPoint = "http://dummy.restapiexample.com/api/v1";

	@GetMapping("/{employeeId}")
	public ResponseEntity<?> getEmployee(@PathVariable Long employeeId){
		try{
			EmployeeResponse employeeResponse = webClient.build().get()
				.uri(endPoint+"/employee/"+employeeId)
				.retrieve()
				.onStatus(HttpStatus::is4xxClientError, response -> Mono.error(new ServiceException("Too many requests to the external server.")))
				.onStatus(HttpStatus::is5xxServerError, response -> Mono.error(new ServiceException("External server error.")))
				.bodyToMono(EmployeeResponse.class)
				.block();
			EmployeeDto employeeDto = employeeResponse.getData();
			return ResponseEntity.ok(employeeDto);
		}
		catch(Exception getEmployeeDtoException) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("\tIt was not possible to retrieve the employee's data from the external server. " + getEmployeeDtoException.getMessage());
		}
	}

	@GetMapping(value={"/", ""})
	public ResponseEntity<?> getEmployees(){
		try{
			EmployeesResponse employeesResponse = webClient.build().get()
				.uri(endPoint+"/employees")
				.retrieve()
				.onStatus(HttpStatus::is4xxClientError, response -> Mono.error(new ServiceException("Too many requests to the external server.")))
				.onStatus(HttpStatus::is5xxServerError, response -> Mono.error(new ServiceException("External server error.")))
				.bodyToMono(EmployeesResponse.class)
				.block();
			EmployeeDto[] employeesDto = employeesResponse.getData();
			for(EmployeeDto employeeDto : employeesDto){
				employeeDto.setEmployee_annual_salary( employeeDtoService.calculateAnnualSalary(employeeDto) );
			}
			return ResponseEntity.ok(employeesDto);
		}
		catch(Exception getEmployeesDtoException) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("\tIt was not possible to retrieve the employees list from the external server. " + getEmployeesDtoException.getMessage());
		}
	}
}

/**
 * 
 */
package com.swapnil.learning.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.swapnil.learning.dto.EmployeeDto;
import com.swapnil.learning.exception.ResourceNotFoundException;
import com.swapnil.learning.model.Employee;
import com.swapnil.learning.repository.EmployeeRepository;

/**
 * @author Swapnil Dangore
 */
@RestController
public class EmployeeController {

	@Autowired
    private ModelMapper modelMapper;
	
	@Autowired
    private EmployeeRepository employeeRepository;
	
	@GetMapping("/employees")
	public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
		return ResponseEntity.ok().body(
				employeeRepository.findAll()
				.stream()
				.map(this::convertToDto)
				.collect(Collectors.toList())
		);
	}
	
	@GetMapping("/v2/employees")
	public ResponseEntity<List<EmployeeDto>> getAllEmployeesV2() {
		return ResponseEntity.ok().body(
				employeeRepository.findAll()
				.stream()
				.map(this::convertToDto)
				.collect(Collectors.toList())
		);
	}
	
	@GetMapping("/employees/{id}")
    public ResponseEntity < EmployeeDto > getEmployeeById(@PathVariable(value = "id") int employeeId)
    throws ResourceNotFoundException {		
        Employee employee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        return ResponseEntity.ok().body(convertToDto(employee));
    }

    @PostMapping("/employees")
    public EmployeeDto createEmployee(@Validated @RequestBody EmployeeDto empDto) {
    	return convertToDto(employeeRepository.save(convertToModel(empDto)));
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity < EmployeeDto > updateEmployee(@PathVariable(value = "id") int employeeId,
        @Validated @RequestBody EmployeeDto empDto) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
       
        employee.setEmployeeEmail(empDto.getEmployeeEmail());
        employee.setEmployeeLastName(empDto.getEmployeeLastName());
        employee.setEmployeeFirstName(empDto.getEmployeeFirstName());
        employee.setEmployeeAge(empDto.getEmployeeAge());
        
        final Employee updatedEmployee = employeeRepository.save(employee);
        
        return ResponseEntity.ok(convertToDto(updatedEmployee));
    }

    @DeleteMapping("/employees/{id}")
    public Map < String, Boolean > deleteEmployee(@PathVariable(value = "id") int employeeId)
    throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        employeeRepository.delete(employee);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
    
    private EmployeeDto convertToDto(Employee emp) {
    	return modelMapper.map(emp,EmployeeDto.class);
    }
    
    private Employee convertToModel(EmployeeDto empDto) {
    	return modelMapper.map(empDto,Employee.class);
    }
}

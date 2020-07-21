/**
 * 
 */
package com.swapnil.learning.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.swapnil.learning.exception.ResourceNotFoundException;
import com.swapnil.learning.model.Employee;
import com.swapnil.learning.repository.EmployeeRepository;

/**
 * @author Swapnil Dangore
 */
@RestController
//@RequestMapping(path="/employees")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

	@Autowired
    private EmployeeRepository employeeRepository;
	
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		return ResponseEntity.ok().body(employeeRepository.findAll());
	}
	
	@GetMapping("/v2/employees")
	public ResponseEntity<List<Employee>> getAllEmployeesV2() {
		return ResponseEntity.ok().body(employeeRepository.findAll());
	}
	
	@GetMapping("/employees/{id}")
    public ResponseEntity < Employee > getEmployeeById(@PathVariable(value = "id") int employeeId)
    throws ResourceNotFoundException {		
        Employee employee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        return ResponseEntity.ok().body(employee);
    }

    @PostMapping("/employees")
    public Employee createEmployee(@Validated @RequestBody Employee employee) {
    	return employeeRepository.save(employee);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity < Employee > updateEmployee(@PathVariable(value = "id") int employeeId,
        @Validated @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        employee.setEmployeeEmail(employeeDetails.getEmployeeEmail());
        employee.setEmployeeLastName(employeeDetails.getEmployeeLastName());
        employee.setEmployeeFirstName(employeeDetails.getEmployeeFirstName());
        employee.setEmployeeAge(employeeDetails.getEmployeeAge());
        
        final Employee updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
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
}

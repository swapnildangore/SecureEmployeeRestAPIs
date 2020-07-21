/**
 * 
 */
package com.swapnil.learning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.swapnil.learning.model.Employee;

/**
 * 
 * @author Swapnil Dangore
 *
 */
@Repository
public interface EmployeeRepository extends  JpaRepository<Employee, Integer> {

	@Query("select emp from Employee emp where emp.employeeEmail = ?1")
	public Employee findByEmailAddress(String emailAddress);
	
	@Query("from Employee emp where emp.employeeFirstName = :firstName or emp.employeeLastName= :lastName")
	public List<Employee> findByLastNameOrFirstName(String firstName,String lastName);
	
	@Query("from Employee emp where emp.employeeFirstName like ?1")
	public List<Employee> findByFirstNameLike(String firstName);
	
	@Query("from Employee emp where emp.employeeLastName <> ?1")
	public List<Employee> findByLastNameNot(String lastName);
	
	@Query("from Employee emp where emp.employeeAge in ?1")
	public List<Employee> findByAgeIn(List<Integer> ages);
	
	@Query("from Employee emp where emp.employeeAge > ?1")
	public List<Employee> findByAgeGreaterThan(int age);
}

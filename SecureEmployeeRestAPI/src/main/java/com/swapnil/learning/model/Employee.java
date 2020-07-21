/**
 * 
 */
package com.swapnil.learning.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Swapnil Dangore
 *
 */
@Entity
@Table(name = "EMPLOYEES")
public class Employee{

	private int employeeId;
	private String employeeFirstName;
	private String employeeLastName;
	private int  employeeAge;
	private String employeeEmail;
	/**
	 * @return the employeeId
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	public int getEmployeeId() {
		return employeeId;
	}
	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	/**
	 * @return the employeeFirstName
	 */
	@Column(name = "FIRST_NAME", nullable = false)
	public String getEmployeeFirstName() {
		return employeeFirstName;
	}
	/**
	 * @param employeeFirstName the employeeFirstName to set
	 */
	public void setEmployeeFirstName(String employeeFirstName) {
		this.employeeFirstName = employeeFirstName;
	}
	/**
	 * @return the employeeLastName
	 */
	@Column(name = "LAST_NAME", nullable = false)
	public String getEmployeeLastName() {
		return employeeLastName;
	}
	/**
	 * @param employeeLastName the employeeLastName to set
	 */
	public void setEmployeeLastName(String employeeLastName) {
		this.employeeLastName = employeeLastName;
	}
	/**
	 * @return the employeeAge
	 */
	@Column(name = "AGE", nullable = false)
	public int getEmployeeAge() {
		return employeeAge;
	}
	/**
	 * @param employeeAge the employeeAge to set
	 */
	public void setEmployeeAge(int employeeAge) {
		this.employeeAge = employeeAge;
	}
	/**
	 * @return the employeeEmail
	 */
	@Column(name = "EMAIL", nullable = false)
	public String getEmployeeEmail() {
		return employeeEmail;
	}
	/**
	 * @param employeeEmail the employeeEmail to set
	 */
	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}
	
}

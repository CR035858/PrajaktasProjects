package com.gl.aopproj.service;

import java.util.Optional;

import com.gl.aopproj.entity.Employee;


public interface EmployeeService {

	void addEmployee(Employee employee);

	void updateEmployee(Employee employee);

	Optional<Employee> getEmployeeById(Integer id);
	 
	void deleteEmployeeByid(int id);

}
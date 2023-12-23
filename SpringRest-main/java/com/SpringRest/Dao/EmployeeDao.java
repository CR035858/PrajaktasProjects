package com.SpringRest.Dao;

import java.util.List;

import com.SpringRest.Entity.Employee;


public interface EmployeeDao {
	public List<Employee> findAll(); 
	
	public Employee findById(int theId);
	
	public void save(Employee theEmployee);
	
	public void deleteById(int theId);
	
	public void updateEmployee(Employee employee);
}

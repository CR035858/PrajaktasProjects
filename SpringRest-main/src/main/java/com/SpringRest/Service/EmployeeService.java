package com.SpringRest.Service;

import java.util.List;
import com.SpringRest.Entity.Employee;


public interface EmployeeService {
	
	public List<Employee> findAll();
	
	Employee findById(int theId);
	
	public abstract void save(Employee theEmployee);
	
	public void deleteById(int theId);

	public void update(Employee employee);	
}

package com.greatlearning.employee.service;
import java.util.List;
import com.greatlearning.employee.data.Employee;

public interface EmployeeService {
	
	List<Employee> getAllEmployees();
	void addNewEmployee(Employee employee);
	void updateEmployee(Employee employee);
	void deleteEmployeeById(int empId);
	Employee getEmployeeById(int empId);

}

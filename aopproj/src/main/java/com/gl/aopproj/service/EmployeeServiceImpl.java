package com.gl.aopproj.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.aopproj.entity.Employee;
import com.gl.aopproj.repository.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public void addEmployee(Employee employee) {
		employeeRepository.saveAndFlush(employee);
	}

	@Override
	public void updateEmployee(Employee employee) {
		boolean exist = employeeRepository.existsById(employee.getId());
		if (exist) {
			employeeRepository.saveAndFlush(employee);
		} else {
			throw new RuntimeException("There is not employee with id " + employee.getId());
		}
	}

	@Override
	public Optional<Employee> getEmployeeById(Integer id) {
		return employeeRepository.findById(id);
	}

	@Override
	public void deleteEmployeeByid(int id) {
		employeeRepository.deleteById(id);
	}
}

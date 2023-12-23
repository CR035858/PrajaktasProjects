package com.SpringRest.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.SpringRest.Dao.EmployeeDao;
import com.SpringRest.Entity.Employee;


@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	private EmployeeDao employeeDao;
	
	@Autowired
	public EmployeeServiceImpl(EmployeeDao employeeDao) {             //Parameterized Constructor
		this.employeeDao = employeeDao;
	}

	@Override
	public List<Employee> findAll() {
		return employeeDao.findAll();
	}

	@Override
	public Employee findById(int theId) {
		return employeeDao.findById(theId);
	}

	@Override
	public void save(Employee theEmployee) {
		employeeDao.save(theEmployee);
	}

	@Override
	public void deleteById(int theId) {
	    employeeDao.deleteById(theId);
	}


	@Override
	public void update(Employee employee) {
		employeeDao.updateEmployee(employee);	
	}
}

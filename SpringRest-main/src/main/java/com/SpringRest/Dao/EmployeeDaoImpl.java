package com.SpringRest.Dao;

import java.util.List;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.SpringRest.Entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;


@Repository
public class EmployeeDaoImpl implements EmployeeDao {
	
	private EntityManager entityManager;
	
	@Autowired
	public EmployeeDaoImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	public List<Employee> findAll() {                               //Get the current Hibernate Session
		Session currentSession = entityManager.unwrap(Session.class);
		Query theQuery = currentSession.createQuery("from Employee", Employee.class);
		List<Employee> employees = theQuery.getResultList();
		return employees;
	}

	@Override
	public Employee findById(int theId) {
		Session currentSession = entityManager.unwrap(Session.class);
		Employee employee = currentSession.get(Employee.class, theId);
		return employee;
	}

	@Override
	public void save(Employee theEmployee) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.save(theEmployee);
	}

	@Override
	public void deleteById(int theId) {
	    Session currentSession = entityManager.unwrap(Session.class);
	    org.hibernate.Transaction tx = currentSession.beginTransaction();
	    Query theQuery = currentSession.createQuery("DELETE FROM Employee WHERE id = :employeeId");
	    theQuery.setParameter("employeeId", theId);
	    theQuery.executeUpdate();
	    tx.commit();
	}

	@Override
	public void updateEmployee(Employee employee) {
		 Session currentSession = entityManager.unwrap(Session.class);
		 org.hibernate.Transaction tx = currentSession.beginTransaction();
		 currentSession.saveOrUpdate(employee);
		 tx.commit();
	}
}

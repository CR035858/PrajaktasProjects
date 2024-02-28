package com.gl.aopproj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gl.aopproj.entity.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
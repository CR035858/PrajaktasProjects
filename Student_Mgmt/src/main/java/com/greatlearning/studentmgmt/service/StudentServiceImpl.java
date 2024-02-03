package com.greatlearning.studentmgmt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatlearning.studentmgmt.entity.Student;
import com.greatlearning.studentmgmt.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService{
	@Autowired
	StudentRepository repository;
	@Override
	public List<Student> getAll() {
		return repository.findAll();
	}
	@Override
	public void create(Student student) {
		repository.save(student);
	}
	@Override
	public void update(Student student) {
		repository.save(student);
	}
	@Override
	public void deleteById(int studId) {
		repository.deleteById(studId);	
	}
	@Override
	public Student getById(int studId) {
		return repository.findById(studId).orElse(null);
	}

}

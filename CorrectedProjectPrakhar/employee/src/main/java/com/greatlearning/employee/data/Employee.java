package com.greatlearning.employee.data;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import lombok.Setter;
import lombok.Getter;
@Entity
@Table (name="employees")
@Getter
@Setter
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name= "first_name")
	private String firstName;
	@Column(name= "last_name")
	private String lastName;
	@Column(name= "email")
	private String email;
	
}
	



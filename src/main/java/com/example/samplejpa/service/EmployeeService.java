package com.example.samplejpa.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import com.example.samplejpa.entity.Employee;

@Service
public class EmployeeService {

	@PersistenceContext
	private EntityManager entityManager;

	public List<Employee> getEmployeeList() {
		return entityManager.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
	}
}

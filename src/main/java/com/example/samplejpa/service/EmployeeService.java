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

	public <T> T cast(Object o) {
		return (T) o;
	}

	public List<Employee> getEmployeeList() {
		List<Employee> list = entityManager.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
		list = entityManager.createNativeQuery("SELECT * FROM jpa.employee", Employee.class).getResultList();
		list = cast(entityManager.createNativeQuery("SELECT * FROM jpa.employee", Employee.class).getResultList());
		return list;
	}
}

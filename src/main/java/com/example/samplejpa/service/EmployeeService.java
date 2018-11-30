package com.example.samplejpa.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.samplejpa.entity.Employee;
import com.example.samplejpa.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private EmployeeRepository employeeRepository;

	public <T> T cast(Object o) {
		return (T) o;
	}

	public List<Employee> getEmployeeList() {
		List<Employee> list = entityManager.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
		list = entityManager.createNativeQuery("SELECT * FROM jpa.employee", Employee.class).getResultList();
		list = cast(entityManager.createNativeQuery("SELECT * FROM jpa.employee", Employee.class).getResultList());
		return list;
	}

	public Optional<Employee> findNoOne() {
		// Repositoryで JpaSpecificationExecution の implementsが必要
		Optional<Employee> employeeOpt = employeeRepository.findOne(new Specification<Employee>() {
			@Override
			public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("no"), 1);
			}
		});
		// Lambdaでの記述
		employeeOpt = employeeRepository.findOne((root, query, cb) -> cb.equal(root.<Integer>get("no"), 1));
		return employeeOpt;
	}

	public List<Employee> getEmployeeList(Employee employee) {
		return employeeRepository.findAll(new Specification<Employee>() {
			@Override
			public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("no"), 1);
			}
		});
	}

	public Page<Employee> search(Pageable pageable) {
		return employeeRepository.search(pageable);
	}
}

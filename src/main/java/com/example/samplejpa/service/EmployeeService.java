package com.example.samplejpa.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.samplejpa.entity.Employee;
import com.example.samplejpa.entity.Employee.Sex;
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
		List<Employee> list = new ArrayList<>();
		list = entityManager.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
		list = entityManager.createNativeQuery("SELECT * FROM jpa.employee", Employee.class).getResultList();
		list = cast(entityManager.createNativeQuery("SELECT * FROM jpa.employee", Employee.class).getResultList());
		list = employeeRepository.findAllById(Arrays.asList(1, 2));
		list = employeeRepository.findAll(Sort.by(Order.asc("mailAddress"), Order.asc("no")));

		Employee employee = new Employee();
		Example<Employee> employeeExample = Example.of(employee);

		employee.setSex(Sex.female);
		// Null値のカラムを条件に加える
		employeeExample = Example.of(employee, ExampleMatcher.matching().withIncludeNullValues());
		// Null値のカラムを条件に加えない
		employeeExample = Example.of(employee, ExampleMatcher.matching().withIgnoreNullValues());

		// メールアドレスの部分一致検索
		employee = new Employee();
		employee.setMailAddress("yamada");
		employeeExample = Example.of(employee, ExampleMatcher.matching().withMatcher("mailAddress",
				new ExampleMatcher.GenericPropertyMatcher().contains()));

		list = employeeRepository.findAll(employeeExample);

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

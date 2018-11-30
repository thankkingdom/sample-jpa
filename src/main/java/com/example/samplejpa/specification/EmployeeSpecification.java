package com.example.samplejpa.specification;

import org.springframework.data.jpa.domain.Specification;

import com.example.samplejpa.entity.Department;
import com.example.samplejpa.entity.Employee;

public class EmployeeSpecification {

	public static Specification<Employee> firstName(String firstName) {
		return firstName == null || firstName.isEmpty() ? null
				: (root, query, cb) -> cb.like(root.<String>get("firstName"), "%" + firstName + "%");
	}

	public static Specification<Employee> lastName(String lastName) {
		return lastName == null || lastName.isEmpty() ? null
				: (root, query, cb) -> cb.like(root.<String>get("lastName"), "%" + lastName + "%");
	}

	public static Specification<Employee> sex(Employee.Sex sex) {
		return sex == null ? null : (root, query, cb) -> cb.equal(root.<Employee.Sex>get("sex"), sex);
	}

	public static Specification<Employee> birthdayEquals(java.sql.Date birthday) {
		return birthday == null ? null : (root, query, cb) -> cb.equal(root.<java.sql.Date>get("birthday"), birthday);
	}

	public static Specification<Employee> mailAddress(String mailAddress) {
		return mailAddress == null || mailAddress.isEmpty() ? null
				: (root, query, cb) -> cb.like(root.<String>get("mailAddress"), "%" + mailAddress + "%");
	}

	public static Specification<Employee> department(Department department) {
		return department == null || department.getCode() == null ? null
				: (root, query, cb) -> cb.equal(root.<Department>get("department").<Integer>get("code"),
						department.getCode());
	}
}

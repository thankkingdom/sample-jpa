package com.example.samplejpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.samplejpa.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}

package com.example.samplejpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.samplejpa.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}

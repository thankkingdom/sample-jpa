package com.example.samplejpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "department", schema = "jpa")
public class Department {
	@Id
	@GeneratedValue
	private Integer code;

	@Column(length = 20, nullable = false)
	private String name;
}

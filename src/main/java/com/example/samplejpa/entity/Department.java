package com.example.samplejpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Department {
	@Id
	@GeneratedValue
	private Integer code;

	@Column(length = 20, nullable = false)
	private String name;
}

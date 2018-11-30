package com.example.samplejpa.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "employee", schema = "jpa")
public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;

	public static enum Sex {
		male, female
	}

	@Id
	@GeneratedValue
	private Integer no;

	@Column(length = 20, nullable = false)
	private String firstName;

	@Column(length = 20, nullable = false)
	private String lastName;

	@Column(length = 10)
	@Enumerated(EnumType.STRING)
	private Sex sex;

	private Date birthday;

	@Column(unique = true)
	private String mailAddress;

	@ManyToOne(optional = false)
	@JoinColumn(name = "departmentCode")
	private Department department;
}

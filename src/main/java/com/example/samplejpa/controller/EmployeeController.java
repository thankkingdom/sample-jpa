package com.example.samplejpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.samplejpa.service.EmployeeService;

@Controller
@RequestMapping("employee")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@RequestMapping("/")
	public String index() {
		return "employee/index";
	}

	@RequestMapping("list")
	public String list(Model model) {
		model.addAttribute("employees", employeeService.getEmployeeList());
		return "employee/list";
	}
}

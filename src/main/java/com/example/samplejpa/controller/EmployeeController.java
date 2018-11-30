package com.example.samplejpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
	public String list(Model model, Pageable pageable) {
		model.addAttribute("employees", employeeService.getEmployeeList());

		// List<Order> orders = new ArrayList<>();
		// orders.add(Order.asc("email"));
		// Sort sort = Sort.by((String[]) orders.toArray());
		model.addAttribute("list", employeeService.search(pageable));
		return "employee/list";
	}
}

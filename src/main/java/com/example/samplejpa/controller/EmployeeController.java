package com.example.samplejpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.samplejpa.entity.Employee;
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
		model.addAttribute("employee", new Employee());
		model.addAttribute("departments", employeeService.getDepartmentList());
		return "employee/list";
	}

	@RequestMapping("edit")
	public String edit(@RequestParam(name = "no") Integer no, Model model) {
		model.addAttribute("employee", employeeService.getEmployee(no).orElse(new Employee()));
		model.addAttribute("departments", employeeService.getDepartmentList());
		return "employee/edit";
	}

	@RequestMapping("save")
	public String save(@ModelAttribute Employee employee, Model model, Errors errors) {
		try {
			employeeService.saveEmployee(employee);
		} catch (OptimisticLockingFailureException e) {
			errors.reject("error.lock.optimistic");
			model.addAttribute("departments", employeeService.getDepartmentList());
			return "employee/edit";
		}
		return "redirect:list";
	}
}

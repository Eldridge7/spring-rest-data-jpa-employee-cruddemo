package com.luv2code.springbootcrmdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springbootcrmdemo.entity.Employee;
import com.luv2code.springbootcrmdemo.service.EmployeeService;


@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	private EmployeeService employeeService;
	
	// quick and dirty: inject employee dao
	
	@Autowired
	public EmployeeRestController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	// expose "/employees" and return list of employees
	@GetMapping("/employees")
	public List<Employee> findAll(){
		return employeeService.findAll();
	}
	
	
	// add mapping to get a single employee "/employees/{employeeId}"
	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@PathVariable int employeeId){
		
		Employee theEmployee = employeeService.findById(employeeId);
		
		if(theEmployee == null) {
			throw new RuntimeException("Employee id not found - " + employeeId);
		}
		
		return theEmployee;
	}
	
	// add mapping for POST /employees - add new employees
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee theEmployee) {
		
		// also just in case they pass an id in JSON ... set id to 0
		// this is to force a save of new item ... instead of update
		
		theEmployee.setId(0);
		
		employeeService.save(theEmployee);
		
		return theEmployee;
	}
	
	// add mapping for PUT /employees - update existing employee
	
	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee theEmployee) {
		
		employeeService.save(theEmployee);
		
		return theEmployee;
	}
	
	// add mapping for DELETE /employees/{employeeId} - delete existing employee
	
		@DeleteMapping("/employees/{employeeId}")
		public String deleteEmployee(@PathVariable int employeeId) {
			
			Employee theEmployee = employeeService.findById(employeeId);
			
			if(theEmployee == null) {
				throw new RuntimeException("Employee id not found - " + employeeId);
			}
			
			
			employeeService.deleteById(employeeId);
			
			return "Deleted employee id - " + employeeId;
			
		}
	
	
}


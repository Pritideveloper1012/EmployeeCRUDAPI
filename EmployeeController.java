package com.self.in.controller;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.self.in.model.Employee;
import com.self.in.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	public EmployeeController(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}
	@PostMapping("/employee")
	public String createNewEmployee( @RequestBody Employee employee) {
		employeeRepository.save(employee);
		return "Employee craeted in database";
	}
	@GetMapping("/employee")
	public ResponseEntity<List<Employee>> getAllEmployee(){
		List<Employee> empList=new ArrayList<>();
		employeeRepository.findAll().forEach(empList::add);
		return new ResponseEntity<List<Employee>>(empList,HttpStatus.OK);
		
	}
	@GetMapping("/employee/{empId}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable long empId){
		
		Optional<Employee> emp=employeeRepository.findById(empId);
		if (emp.isPresent()) {
			return new ResponseEntity<Employee>(emp.get(),HttpStatus.FOUND);
			}
		else {
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}
		
		
	}
	@PutMapping("/employee/{empId}")
	public String upadteEmployeeById(@PathVariable long empId, @RequestBody Employee employee) {
		Optional<Employee>  empOptional=employeeRepository.findById(empId);
		if (empOptional.isPresent()) {
			Employee exitEmployee=empOptional.get();
			//exitEmployee.setId(employee.getId());
			exitEmployee.setEmpName(employee.getEmpName());
			exitEmployee.setEmpCity(employee.getEmpCity());
			exitEmployee.setEmpAge(employee.getEmpAge());
			exitEmployee.setEmpSalary(employee.getEmpSalary());
			employeeRepository.save(exitEmployee);
			return "Employee Details against Id " + empId + " updated";
			}
		else {
			return "Employee Details does not exist for empId " + empId;
		}
		
	}
	@DeleteMapping("/employee/{empId}")
	public String deleteById(@PathVariable long empId) {
		employeeRepository.deleteById(empId);
		return "Employee Deleted Successfully";
	}
	@DeleteMapping("/employee")
	
	public String deleteAllEmployee() {
		employeeRepository.deleteAll();
		return "Employee Deleted Successfully";
	}

//	public List<Employee> getEmployeesByCity(@PathVariable String city){
//		
//	}
	@GetMapping("/employee/empCity")
	public ResponseEntity<List<Employee>> getAllEmployeeCity(){
		List<String> lcity=new ArrayList<>();
		if (lcity.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.FOUND);
			}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		}
	
	
	
	

}

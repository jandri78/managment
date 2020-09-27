package com.andres.interview.service;

import java.text.ParseException;
import java.util.List;

import com.andres.interview.model.Employee;
import com.andres.interview.model.Payment;

public interface IEmployeeService {

	public List<Employee> getAllEmployees();
	
	public List<Employee> getEmloyeeByNameOrLastName(String term);
	
	public Employee getEmloyeeById(Long id);
	
	public Employee saveEmployee(Employee employee);
	
	public Employee updateEmployee(Employee employee);
	
	public Employee deleteEmployee(Long id);
	
	public Payment calcular(Employee employee, String fechaCalcular) throws ParseException;
	
}

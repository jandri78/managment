package com.andres.interview.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.andres.interview.exceptions.EmployeeErrorDescription;
import com.andres.interview.model.Employee;
import com.andres.interview.service.IEmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private IEmployeeService employeeService;

	@GetMapping
	public List<Employee> get() {
		return employeeService.getAllEmployees();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getById(@PathVariable Long id) {
		Employee employee = employeeService.getEmloyeeById(id);

		if (Objects.isNull(employee)) {
			return new ResponseEntity<Object>(new EmployeeErrorDescription(HttpStatus.NOT_FOUND, "Employee NOT FOUND"),
					HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Object>(employee, HttpStatus.OK);
	}

	@GetMapping("/byname")
	public List<Employee> getByNameOrLastname(@RequestParam(value = "nameORlastname") String term) {
		return employeeService.getEmloyeeByNameOrLastName(term);
	}

	@PostMapping
	public ResponseEntity<Object> post(@RequestBody Employee employee) {

		if (Objects.isNull(employee.getNombres()) || Objects.isNull(employee.getApellidos())
				|| Objects.isNull(employee.getSalarioBase()) || employee.getSalarioBase() <= 0) {
			return new ResponseEntity<Object>(
					new EmployeeErrorDescription(HttpStatus.BAD_REQUEST,
							"incomplete information for creation please add name or lastname or salary"),
					HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Object>(employeeService.saveEmployee(employee), HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<Object> put(@RequestBody Employee employee) {

		if (Objects.isNull(employee.getNombres()) || Objects.isNull(employee.getApellidos())
				|| Objects.isNull(employee.getSalarioBase())) {
			return new ResponseEntity<Object>(
					new EmployeeErrorDescription(HttpStatus.BAD_REQUEST,
							"incomplete information for creation please add name or lastname or salary"),
					HttpStatus.BAD_REQUEST);
		}

		Employee updatedEmpl = employeeService.updateEmployee(employee);

		if (Objects.isNull(updatedEmpl)) {
			return new ResponseEntity<Object>(new EmployeeErrorDescription(HttpStatus.NOT_FOUND, "Employee NOT FOUND"),
					HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Object>(updatedEmpl, HttpStatus.ACCEPTED);
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteById(@PathVariable Long id) {

		Employee employee = employeeService.deleteEmployee(id);

		if (Objects.isNull(employee)) {
			return new ResponseEntity<Object>(new EmployeeErrorDescription(HttpStatus.NOT_FOUND, "Employee NOT FOUND"),
					HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Object>(employee, HttpStatus.ACCEPTED);
	}

	@PostMapping("/calcular")
	public ResponseEntity<Object> calcularPago(@RequestParam(value = "id") long id,
			@RequestParam(value = "month") String month, @RequestParam(value = "year") int year) throws ParseException {
		Employee employee = employeeService.getEmloyeeById(id);

		if (Objects.isNull(employee)) {
			return new ResponseEntity<Object>(new EmployeeErrorDescription(HttpStatus.NOT_FOUND, "Employee NOT FOUND"),
					HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Object>(employeeService.calcular(employee, year + "-" + month), HttpStatus.OK);

	}

}

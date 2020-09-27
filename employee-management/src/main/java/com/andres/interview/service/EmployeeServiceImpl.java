package com.andres.interview.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andres.interview.model.Employee;
import com.andres.interview.model.Payment;
import com.andres.interview.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private PaymentService paymentService;

	@Override
	public List<Employee> getAllEmployees() {

		return employeeRepository.findAll();
	}

	@Override
	public List<Employee> getEmloyeeByNameOrLastName(String term) {
		return employeeRepository.findByNombreOrApellido(term);
	}

	@Override
	public Employee getEmloyeeById(Long id) {
		Optional<Employee> employee = employeeRepository.findById(id);
		if (employee.isPresent()) {
			return employee.get();
		} else {
			return null;
		}

	}

	@Override
	public Employee saveEmployee(Employee employee) {
		employee.setFechaIngreso(new Date());
		return employeeRepository.save(employee);
	}

	@Override
	public Employee updateEmployee(Employee employee) {

		Employee employeeToUpdate = getEmloyeeById(employee.getId());

		if (employeeToUpdate != null) {
			return employeeRepository.save(employee);
		} else {
			return null;
		}

	}

	@Override
	public Employee deleteEmployee(Long id) {

		Employee employeeToDelete = getEmloyeeById(id);

		if (employeeToDelete != null) {
			employeeRepository.deleteById(id);
			return employeeToDelete;
		} else {
			return null;
		}

	}

	public Payment calcular(Employee employee, String fechaCalcular) throws ParseException {
		Payment payment = new Payment();
		try {

			
			payment.setFechaIngreso(employee.getFechaIngreso().toString());
			payment.setFechaRetiro(
					Objects.nonNull(employee.getFechaRetiro()) ? employee.getFechaRetiro().toString() : "");
			payment.setEmployeeId(employee.getId());
			payment.setNombres(employee.getNombres());
			payment.setApellidos(employee.getApellidos());
			payment.setSalarioBase(employee.getSalarioBase());

			String fechaingreso = employee.getFechaIngreso().toString();
			String fechaRetiro = null;

			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM");
			Date date11 = format1.parse(fechaingreso);
			Date date22 = format1.parse(fechaCalcular);
			Date date33 = null;

			if (Objects.nonNull(employee.getFechaRetiro())) {

				fechaRetiro = employee.getFechaRetiro().toString();
				date33 = format1.parse(fechaRetiro);
			}

			SimpleDateFormat format12 = new SimpleDateFormat("yyyy-MM-dd");

			int diasTrabajadosMes = 30;

			float sueldoDia = employee.getSalarioBase() / 30;

			if (date11.equals(date22)) {
				Date dat1 = format12.parse(fechaingreso);
				Calendar myCal = new GregorianCalendar();
				myCal.setTime(dat1);
				diasTrabajadosMes = 30 - myCal.get(Calendar.DAY_OF_MONTH);

				payment.setDiasCalculados(diasTrabajadosMes);
				payment.setSalario_pagar(sueldoDia * diasTrabajadosMes);

				return payment;

			} else if (Objects.nonNull(date33) && date33.equals(date22)) {

				Date dat1 = format12.parse(fechaRetiro);
				Calendar myCal = new GregorianCalendar();
				myCal.setTime(dat1);
				diasTrabajadosMes = myCal.get(Calendar.DAY_OF_MONTH);

				payment.setDiasCalculados(diasTrabajadosMes);
				payment.setSalario_pagar(sueldoDia * diasTrabajadosMes);

				return payment;
			} else if (date22.before(date11)) {

				payment.setDiasCalculados(0);
				payment.setSalario_pagar(0);

				return payment;
			}

			else {

				payment.setDiasCalculados(diasTrabajadosMes);
				payment.setSalario_pagar(sueldoDia * diasTrabajadosMes);
				return payment;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			paymentService.save(payment);
		}
		return payment;
	}

}

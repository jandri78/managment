package com.andres.interview.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "payment")
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long employeeId;
	private String nombres;
	private String apellidos;
	private float salarioBase;
	private String fechaIngreso;
	private String fechaRetiro;
	private float salario_pagar;
	private int diasCalculados;

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public float getSalarioBase() {
		return salarioBase;
	}

	public void setSalarioBase(float salarioBase) {
		this.salarioBase = salarioBase;
	}

	public String getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public String getFechaRetiro() {
		return fechaRetiro;
	}

	public void setFechaRetiro(String fechaRetiro) {
		this.fechaRetiro = fechaRetiro;
	}

	public float getSalario_pagar() {
		return salario_pagar;
	}

	public void setSalario_pagar(float salario_pagar) {
		this.salario_pagar = salario_pagar;
	}

	public int getDiasCalculados() {
		return diasCalculados;
	}

	public void setDiasCalculados(int diasCalculados) {
		this.diasCalculados = diasCalculados;
	}

	@Override
	public String toString() {
		return "Payment [employeeId=" + employeeId + ", nombres=" + nombres + ", apellidos=" + apellidos
				+ ", salarioBase=" + salarioBase + ", fechaIngreso=" + fechaIngreso + ", fechaRetiro=" + fechaRetiro
				+ ", salario_pagar=" + salario_pagar + ", diasCalculados=" + diasCalculados + "]";
	}

}

package com.andres.interview.exceptions;

import org.springframework.http.HttpStatus;

public class EmployeeErrorDescription {
	
	private HttpStatus status;
	
	private String description;

	
	public EmployeeErrorDescription(HttpStatus status, String description) {
		super();
		this.status = status;
		this.description = description;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "EmployeeException [status=" + status + ", description=" + description + "]";
	}
	
	

}

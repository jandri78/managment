package com.andres.interview.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.andres.interview.model.Employee;

@Repository
public interface EmployeeRepository  extends JpaRepository<Employee, Long>{
	
	@Query("select e from Employee e where e.nombres like %?1% or e.apellidos like %?1%")
	public List<Employee> findByNombreOrApellido(String term);
	
}

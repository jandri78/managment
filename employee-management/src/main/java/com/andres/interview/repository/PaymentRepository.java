package com.andres.interview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andres.interview.model.Payment;

@Repository
public interface PaymentRepository  extends JpaRepository<Payment, Long>{
	
}

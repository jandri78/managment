package com.andres.interview.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andres.interview.model.Payment;
import com.andres.interview.repository.PaymentRepository;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepository paymentrepo;

	public Optional<Payment> getById(Long id) {
		return paymentrepo.findById(id);
	}

	public Payment save(Payment payment) {

		return paymentrepo.save(payment);
	}

	public List<Payment> get() {
		return paymentrepo.findAll();
	}

}

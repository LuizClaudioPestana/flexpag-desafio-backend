package com.flexpag.paymentscheduler.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RestController;

import com.flexpag.paymentscheduler.enums.PaymentStatus;
import com.flexpag.paymentscheduler.models.Payment;
import com.flexpag.paymentscheduler.repository.PaymentRepository;

@RestController
@RequestMapping(value = "/payment")
public class PaymentRestController {
	@Autowired
	private PaymentRepository repository;
	
	@PostMapping(value = "/create")
	public ResponseEntity<?> createPaymentSchedule(@RequestBody Payment payment) {
		payment.setStatus(PaymentStatus.PENDING);
		Payment ps = repository.save(payment);
		return new ResponseEntity<>(ps, HttpStatus.OK);
	}
	
	@GetMapping(value = "/get-all")
	public ResponseEntity<List<Payment>> getAll() {
		List<Payment> paymentList = new ArrayList<>();
		paymentList = repository.findAll();
		return new ResponseEntity<>(paymentList, HttpStatus.OK);
	}
	
	@GetMapping(path="/get/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {
		Optional<Payment> payment;
		try {
			payment = repository.findById(id);
			return new ResponseEntity<Optional<Payment>>(payment, HttpStatus.OK);
		} catch (NoSuchElementException nsee) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(path="/delete/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		Optional<Payment> payment;
		try {
			payment = repository.findById(id);
			repository.delete(payment.get());
			return new ResponseEntity<> ("Registro deletado!", HttpStatus.OK);
			}catch (NoSuchElementException nsee) {
				return new ResponseEntity<Optional<Payment>>(HttpStatus.NOT_FOUND);
			}
	}
	
	@PutMapping(value="/update/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Payment newPayment) {
		try {
			
			Optional <Payment> p = repository.findById(id);
			p.get().setDueDate(newPayment.getDueDate());
			p.get().setDueValue(newPayment.getDueValue());
			repository.save(p.get());
			return ResponseEntity.ok().body(p);
			
		} catch (Exception e) {
			
			return ResponseEntity.notFound().build();
			
		}
		 
	}
	
}


package com.flexpag.paymentscheduler.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.flexpag.paymentscheduler.enums.PaymentStatus;

@Entity
public class Payment {
	@GeneratedValue
	@Id
	private Long id;
	private double dueValue;
	private Date dueDate;
	private PaymentStatus status;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getDueValue() {
		return dueValue;
	}
	public void setDueValue(double dueValue) {
		this.dueValue = dueValue;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public PaymentStatus getStatus() {
		return status;
	}
	public void setStatus(PaymentStatus status) {
		this.status = status;
	}
	
}

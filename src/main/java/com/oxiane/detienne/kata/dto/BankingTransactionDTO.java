package com.oxiane.detienne.kata.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BankingTransactionDTO {

	private Long id;

	private String type;

	private LocalDateTime date;

	private Double amount;

	// Constructors
	public BankingTransactionDTO() {
	}

	// Getters And Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "BankingTransactionDTO [id=" + id + ", type=" + type + ", date=" + date + ", amount=" + amount + "]";
	}

	/*
	 * dynamically calculate value
	 */
	@JsonIgnore
	public Double getValue() {
		final Double amount = (this.getAmount() != null) ? this.getAmount() : 0;
		final Double value = ("withdraw".equals(this.getType())) ? -amount : amount;
		return value;
	}

}

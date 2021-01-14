package com.oxiane.detienne.kata.cucumber;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;

/*
 * For Cucumber Tests
 */
public class BankingTransaction {

	private Long id;

	private String type; // deposit , withdraw

	private LocalDateTime date;

	private Double amount;

	public BankingTransaction(String type, Double amount) {
		super();
		this.id = new Random().nextLong();
		this.type = type;
		this.amount = amount;
		this.date = LocalDateTime.now();
	}

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

	public Double getValue() {
		final Double amount = (this.getAmount() != null) ? this.getAmount() : 0;
		final Double value = ("withdraw".equals(this.getType())) ? -amount : amount;
		return value;
	}

	@Override
	public String toString() {
		return "BankingTransaction [id=" + id + ", type=" + type + ", date=" + date + ", amount=" + amount + "]";
	}

}

package com.oxiane.detienne.kata.model;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModelProperty;

public class BankingTransactionDTO {

	private Long id;

	@ApiModelProperty(notes = "banking transaction type", name = "type", required = true)
	private String type;

	private LocalDateTime date;

	@ApiModelProperty(notes = "amount", name = "amount", required = true)
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

}

package com.oxiane.detienne.kata.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.annotations.ApiModelProperty;

@JsonPropertyOrder({ "id", "active", "balance" })
public class AccountDTO {

	@ApiModelProperty(notes = "bank account identitier", name = "id", required = true, value = "10011100099")
//	@Pattern(regexp="\\d{11}")
	private Long id;

	private Boolean active;

	private Double balance;

	private List<BankingTransactionDTO> bankingTransactions;

	// Constructors

	public AccountDTO() {
	}

	// Getters And Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public List<BankingTransactionDTO> getBankingTransactions() {
		return bankingTransactions;
	}

	public void setBankingTransactions(List<BankingTransactionDTO> bankingTransactions) {
		this.bankingTransactions = bankingTransactions;
	}

	@Override
	public String toString() {
		return "AccountDTO [id=" + id + ", active=" + active + ", balance=" + balance + ", bankingTransactions="
				+ bankingTransactions + "]";
	}

}

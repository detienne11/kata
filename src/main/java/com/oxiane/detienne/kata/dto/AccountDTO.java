package com.oxiane.detienne.kata.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "active", "balance" })
public class AccountDTO {

	private Long id;

	private boolean active;

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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<BankingTransactionDTO> getBankingTransactions() {
		return bankingTransactions;
	}

	public void setBankingTransactions(List<BankingTransactionDTO> bankingTransactions) {
		this.bankingTransactions = bankingTransactions;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", active=" + active + ", balance=" + getBalance() + ", bankingTransactions="
				+ bankingTransactions + "]";
	}

	/*
	 * dynamically calculate balance from banking transactions list
	 */
	public Double getBalance() {

		final List<BankingTransactionDTO> bankingTransactions = this.getBankingTransactions();

		final Double balance = bankingTransactions.stream().map(it -> it.getValue()).reduce(.0, Double::sum);
		return balance;
	}

}

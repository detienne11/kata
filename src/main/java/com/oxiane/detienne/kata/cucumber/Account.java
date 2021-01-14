package com.oxiane.detienne.kata.cucumber;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/*
 * For Cucumber Tests
 */
public class Account implements Serializable {

	private Long id;
	
	private boolean active;
	
	private List<BankingTransaction> bankingTransactions;

	public Account(Long id, boolean active) {
		this.setId(id);
		this.setActive(active);
		this.setBankingTransactions(new ArrayList<>());
	}
	
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

	public List<BankingTransaction> getBankingTransactions() {
		return bankingTransactions;
	}

	public void setBankingTransactions(List<BankingTransaction> bankingTransactions) {
		this.bankingTransactions = bankingTransactions;
	}

	public Double getBalance() {
	
		final List<BankingTransaction> bankingTransactions = this.getBankingTransactions();
		
		Double balance = bankingTransactions.stream()
		.map(it -> it.getValue())
		.reduce(.0, Double::sum);
		
		return balance;		
	}
	
	@Override
	public String toString() {
		return "Account [id=" + id + ", active=" + active + ", bankingTransactions=" + bankingTransactions + "]";
	}	

}

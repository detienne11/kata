package com.oxiane.detienne.kata.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "accounts")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private boolean active;

	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<BankingTransaction> bankingTransactions;

	// default constructor
	public Account() {
		this.bankingTransactions = new ArrayList<>();
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

	@Override
	public String toString() {
		return "Account [id=" + id + ", active=" + active + ", bankingTransactions=" + bankingTransactions + "]";
	}

}

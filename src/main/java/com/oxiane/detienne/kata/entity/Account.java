package com.oxiane.detienne.kata.entity;

import java.util.ArrayList;
import java.util.List;

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
//@NamedNativeQuery(
//	    name = "Account.findWithBalance",
//	    query = "select a.id, a.active, sum(case when t.type='withdraw' then (0-t.amount) else t.amount end) as balance from accounts a left join banking_transactions t on t.account_id = a.id where a.id=?1",
//	    resultClass = Account.class)
public class Account {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Boolean active;
	
	@Column(nullable = false)
	private Double balance;
	
	//@OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@OneToMany(mappedBy = "account", fetch=FetchType.LAZY)
    private List<BankingTransaction> bankingTransactions;

	// default constructor
	public Account() {
		this.bankingTransactions = new ArrayList<>();
//		this.balance = 0d;
	}

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

	public List<BankingTransaction> getBankingTransactions() {
		return bankingTransactions;
	}

	public void setBankingTransactions(List<BankingTransaction> bankingTransactions) {
		this.bankingTransactions = bankingTransactions;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", active=" + active + ", balance=" + getBalance() + ", bankingTransactions="
				+ bankingTransactions + "]";
	}

}

package com.oxiane.detienne.kata.repository;

import com.oxiane.detienne.kata.model.Account;
import com.oxiane.detienne.kata.model.BankingTransaction;

public interface AccountDao {

	/**
	 * Find bank account from his number
	 * 
	 * @param id
	 * @return
	 */
	public Account findById(Long id);
	
	/**
	 * Add a new banking transaction (deposit, withdraw) on a bank account
	 * 
	 * @param id
	 * @param bankingTransactionDTO
	 */	
	public void save(BankingTransaction bankingTransaction);
	
}

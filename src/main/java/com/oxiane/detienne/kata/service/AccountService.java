package com.oxiane.detienne.kata.service;

import com.oxiane.detienne.kata.model.AccountDTO;
import com.oxiane.detienne.kata.model.BankingTransactionDTO;

public interface AccountService {

	/**
	 * Find bank account from his number
	 * 
	 * @param id
	 * @return
	 */
	public AccountDTO findById(Long id);
	
	/**
	 * Add a new banking transaction (deposit, withdraw) on a bank account
	 * 
	 * @param id
	 * @param bankingTransactionDTO
	 */
	public void addBankingTransaction(Long id, BankingTransactionDTO bankingTransactionDTO);
	
}

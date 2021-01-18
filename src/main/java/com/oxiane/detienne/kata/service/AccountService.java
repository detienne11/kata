package com.oxiane.detienne.kata.service;

import java.util.List;

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
	 * Find bank transactions by account
	 * 
	 * @param id
	 * @param page
	 * @param size
	 * @return
	 */
	public List<BankingTransactionDTO> findByAccount(Long id, int page, int size);

	/**
	 * Add a new banking transaction (deposit, withdraw) on a bank account
	 * 
	 * @param id
	 * @param bankingTransactionDTO
	 */
	public void addBankingTransaction(Long id, BankingTransactionDTO bankingTransactionDTO);

}

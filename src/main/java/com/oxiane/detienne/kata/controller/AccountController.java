package com.oxiane.detienne.kata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oxiane.detienne.kata.dto.AccountDTO;
import com.oxiane.detienne.kata.dto.BankingTransactionDTO;
import com.oxiane.detienne.kata.service.AccountService;

@RestController
@RequestMapping("/api/v1")
public class AccountController {

	@Autowired
	private AccountService accountService;

	public AccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	/**
	 * Get bank account detail
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/accounts/{id}")
	public AccountDTO getBankAccount(@PathVariable("id") Long id) {

		AccountDTO account = accountService.findById(id);
		return account;
	}

	/**
	 * Add a banking transaction (deposit, withdraw)
	 * 
	 * @param id
	 * @param bankingTransactionDTO
	 * @return
	 */
	@RequestMapping(value = "/accounts/{id}/bankingtransactions", method = RequestMethod.POST)
	public ResponseEntity<Void> addBankingTransaction(@PathVariable("id") Long id,
			@RequestBody BankingTransactionDTO bankingTransactionDTO) {
		accountService.addBankingTransaction(id, bankingTransactionDTO);
		return ResponseEntity.noContent().build();
	}

}

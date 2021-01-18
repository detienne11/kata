package com.oxiane.detienne.kata.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oxiane.detienne.kata.dto.AccountDTO;
import com.oxiane.detienne.kata.dto.BankingTransactionDTO;
import com.oxiane.detienne.kata.service.AccountService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Account API")
@RestController
@RequestMapping("/accounts")
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
	@ApiOperation(value = "Get bank account detail", response = AccountDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "not found") })
	@GetMapping(value = "/{id}")
	public AccountDTO getBankAccount(@PathVariable("id") @Valid Long id) {

		AccountDTO account = this.getAccountService().findById(id);
		return account;
	}

	/**
	 * Add a banking transaction (deposit, withdraw)
	 * 
	 * @param id
	 * @param bankingTransactionDTO
	 * @return
	 */
	@ApiOperation(value = "Add a banking transaction (deposit, withdraw)")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 403, message = "forbidden"), @ApiResponse(code = 404, message = "not found") })
	@PostMapping(value = "/{id}/bankingtransactions")
	public ResponseEntity<Void> addBankingTransaction(@PathVariable("id") Long id,
			@RequestBody BankingTransactionDTO bankingTransactionDTO) {

		this.getAccountService().addBankingTransaction(id, bankingTransactionDTO);
		return ResponseEntity.noContent().build();
	}

}

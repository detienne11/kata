package com.oxiane.detienne.kata.it;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.oxiane.detienne.kata.model.AccountDTO;
import com.oxiane.detienne.kata.model.BankingTransactionDTO;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StatementStepDefinitions {

	// inputs
	private Long bankAccountId;

	private BankingTransactionDTO bankingTransactionDTO;

	// outputs
	private ResponseEntity<AccountDTO> accountResponse;

	private ResponseEntity<List> bankingTransactionsResponse;

	private HttpStatus accountErrorCodeStatusResponse;

	private HttpStatus bankingTransactionsErrorCodeStatusResponse;

	// Getters / Setters

	public Long getBankAccountId() {
		return bankAccountId;
	}

	public void setBankAccountId(Long bankAccountId) {
		this.bankAccountId = bankAccountId;
	}

	public BankingTransactionDTO getBankingTransactionDTO() {
		return bankingTransactionDTO;
	}

	public void setBankingTransactionDTO(BankingTransactionDTO bankingTransactionDTO) {
		this.bankingTransactionDTO = bankingTransactionDTO;
	}

	public ResponseEntity<AccountDTO> getAccountResponse() {
		return accountResponse;
	}

	public void setAccountResponse(ResponseEntity<AccountDTO> accountResponse) {
		this.accountResponse = accountResponse;
	}

	public ResponseEntity<List> getBankingTransactionsResponse() {
		return bankingTransactionsResponse;
	}

	public void setBankingTransactionsResponse(ResponseEntity<List> bankingTransactionsResponse) {
		this.bankingTransactionsResponse = bankingTransactionsResponse;
	}

	public HttpStatus getAccountErrorCodeStatusResponse() {
		return accountErrorCodeStatusResponse;
	}

	public void setAccountErrorCodeStatusResponse(HttpStatus accountErrorCodeStatusResponse) {
		this.accountErrorCodeStatusResponse = accountErrorCodeStatusResponse;
	}

	public HttpStatus getBankingTransactionsErrorCodeStatusResponse() {
		return bankingTransactionsErrorCodeStatusResponse;
	}

	public void setBankingTransactionsErrorCodeStatusResponse(HttpStatus bankingTransactionsErrorCodeStatusResponse) {
		this.bankingTransactionsErrorCodeStatusResponse = bankingTransactionsErrorCodeStatusResponse;
	}

	// Steps

	@Given("^Bank account (\\d+) exists$")
	public void checkBankAccountExistence(Long id) {
		this.setBankAccountId(id);
	}

	@Given("^Bank account (\\d+) not exists$")
	public void checkBankAccountMissing(Long id) {
		this.setBankAccountId(id);
	}

	@When("^I ask for bank account detail$")
	public void requestBankAccount() {

		// retrieve bank account
		final RestTemplate restTemplate = new RestTemplate();

		try {
			this.accountResponse = restTemplate.getForEntity(
					String.format(APIConstants.GET_ACCOUNTS_PATTERN, this.getBankAccountId()), AccountDTO.class);
		} catch (HttpStatusCodeException e) {
			this.setAccountErrorCodeStatusResponse(e.getStatusCode());
		}

		// retrieve last banking transaction page=0 size=2
		try {
			this.bankingTransactionsResponse = restTemplate.getForEntity(
					String.format(APIConstants.GET_BANKINGTRANSACTIONS_PATTERN, this.getBankAccountId(), 0, 2),
					List.class);
		} catch (HttpStatusCodeException e) {
			this.setBankingTransactionsErrorCodeStatusResponse(e.getStatusCode());
		}

	}

	@Then("^I get detail of bank account (\\d+) : operations, date, amount, balance$")
	public void getBankAccountDetail(Long id) {

		// Check Account response
		assertEquals(HttpStatus.OK, this.getAccountResponse().getStatusCode());
		final AccountDTO account = this.accountResponse.getBody();
		assertNotNull(account);
		assertEquals(id, account.getId());

		// Check Banking Transaction Response
		assertEquals(HttpStatus.OK, this.getBankingTransactionsResponse().getStatusCode());
		assertNotNull(this.getBankingTransactionsResponse().getBody());
		// TODO check banking transaction detail

	}

	@Then("^I get an account not found error$")
	public void throwBankAccountNotFoundError() {
		assertEquals(HttpStatus.NOT_FOUND, this.getAccountErrorCodeStatusResponse());
		assertEquals(HttpStatus.NOT_FOUND, this.getAccountErrorCodeStatusResponse());
	}

}

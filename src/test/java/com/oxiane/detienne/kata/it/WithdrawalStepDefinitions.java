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

public class WithdrawalStepDefinitions {

	// inputs
	private Long bankAccountId;

	private BankingTransactionDTO bankingTransactionDTO;

	// outputs
	private ResponseEntity<Void> bankingTransactionResponse;

	private Double expectedBalance;

	private HttpStatus errorCodeStatusResponse;

	// Getters / Setters

	public Long getBankAccountId() {
		return bankAccountId;
	}

	public void setBankAccountId(Long bankAccountId) {
		this.bankAccountId = bankAccountId;
	}

	public BankingTransactionDTO getBankingTransactionDTO() {
		if (bankingTransactionDTO == null) {
			this.bankingTransactionDTO = new BankingTransactionDTO();
		}
		return bankingTransactionDTO;
	}

	public void setBankingTransactionDTO(BankingTransactionDTO bankingTransactionDTO) {
		this.bankingTransactionDTO = bankingTransactionDTO;
	}

	public ResponseEntity<Void> getBankingTransactionResponse() {
		return bankingTransactionResponse;
	}

	public void setBankingTransactionResponse(ResponseEntity<Void> bankingTransactionResponse) {
		this.bankingTransactionResponse = bankingTransactionResponse;
	}

	public Double getExpectedBalance() {
		return expectedBalance;
	}

	public void setExpectedBalance(Double expectedBalance) {
		this.expectedBalance = expectedBalance;
	}

	public HttpStatus getErrorCodeStatusResponse() {
		return errorCodeStatusResponse;
	}

	public void setErrorCodeStatusResponse(HttpStatus bankingTransactionErrorCodeStatusResponse) {
		this.errorCodeStatusResponse = bankingTransactionErrorCodeStatusResponse;
	}

	// Steps

	@Given("^Bank account (\\d+) exists for withdraw operation$")
	public void checkBankAccountExistence(Long id) {
		this.setBankAccountId(id);
	}

	@Given("^Bank account (\\d+) not exists for withdraw operation$")
	public void checkBankAccountMissing(Long id) {
		this.setBankAccountId(id);
	}

	@When("^I withdraw (\\d+\\.\\d+) on my bank account (\\d+)$")
	public void deposit(Double amount, Long id) {

		// init inputs
		final BankingTransactionDTO bankingTransactionDTO = this.getBankingTransactionDTO();
		bankingTransactionDTO.setType(APIConstants.WITHDRAW_TRANSACTION_TYPE);
		bankingTransactionDTO.setAmount(amount);

		// get initial account
		final RestTemplate restTemplate = new RestTemplate();
		try {
			final ResponseEntity<AccountDTO> initialAccount = restTemplate.getForEntity(
					String.format(APIConstants.GET_ACCOUNTS_PATTERN, this.getBankAccountId()), AccountDTO.class);
			this.setExpectedBalance(Double.sum(initialAccount.getBody().getBalance(), -amount));
		} catch (HttpStatusCodeException e) {
			// skip HttpStatusCodeException for this request (use for get initial balance
			// value), same error throws with next request
		}

		// perform deposit
		try {
			this.setBankingTransactionResponse(restTemplate.postForEntity(
					String.format(APIConstants.POST_BANKINGTRANSACTIONS_PATTERN, this.getBankAccountId()),
					bankingTransactionDTO, Void.class));
		} catch (HttpStatusCodeException e) {
			this.setErrorCodeStatusResponse(e.getStatusCode());
		}

	}

	@Then("^Bank account (\\d+) is updated with a withdraw operation$")
	public void checkBankAccountUpdate(Long id) {

		// check add banking transaction response
		assertEquals(HttpStatus.NO_CONTENT, this.getBankingTransactionResponse().getStatusCode());

		// check updated bank account
		final RestTemplate restTemplate = new RestTemplate();
		final ResponseEntity<AccountDTO> accountResponse = restTemplate.getForEntity(
				String.format(APIConstants.GET_ACCOUNTS_PATTERN, this.getBankAccountId()), AccountDTO.class);
		assertEquals(HttpStatus.OK, accountResponse.getStatusCode());
		final AccountDTO account = accountResponse.getBody();
		System.out.println(account);
		assertNotNull(account);
		assertEquals(this.bankAccountId, account.getId());
		assertEquals(this.expectedBalance, account.getBalance());

		// check last banking transaction
		final ResponseEntity<List> bankingTransactionsResponse = restTemplate.getForEntity(
				String.format(APIConstants.GET_BANKINGTRANSACTIONS_PATTERN, this.getBankAccountId(), 0, 1), List.class);
		assertEquals(HttpStatus.OK, bankingTransactionsResponse.getStatusCode());
		assertNotNull(bankingTransactionsResponse.getBody());
		// TODO check banking transaction detail

	}

	@Then("^I get an account not found error for withdraw operation$")
	public void throwBankAccountNotFoundError() {
		assertEquals(HttpStatus.NOT_FOUND, this.getErrorCodeStatusResponse());
	}

	@Then("^I get an account closed error for withdraw operation$")
	public void throwBankAccountClosedError() {
		assertEquals(HttpStatus.FORBIDDEN, this.getErrorCodeStatusResponse());
	}

}

package com.oxiane.detienne.kata.bdd;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.oxiane.detienne.kata.cucumber.Account;
import com.oxiane.detienne.kata.cucumber.AccountRepository;

public class StatementStepDefinitions{

	private AccountRepository accountRepository;
	
	public AccountRepository getAccountRepository() {
		if (this.accountRepository == null) {
			this.accountRepository = new AccountRepository();
		}
		return accountRepository;
	}

	private Account account;
	
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Given("^Bank account (\\d+) exists$")
	public void checkBankAccountExistence(Long id) {
		final boolean exists = this.getAccountRepository().checkAccountExistence(id);
		assertTrue(exists);
	}
	
	@When("^I ask for detail of bank account (\\d+)$")
	public void requestBankAccount(Long id) {
		this.setAccount(this.getAccountRepository().read(id));
	}
	
	@Then("^I get detail of bank account (\\d+) : operations, date, amount, balance$")
	public void getBankAccountDetail(Long id) {
		final Account account = this.getAccount();
		assertNotNull(account);
		assertEquals(account.getId(), id);
	}
	
	@Given("^Bank account (\\d+) not exists$")
	public void checkBankAccountMissing(Long id) {
		final boolean exists = this.getAccountRepository().checkAccountExistence(id);
		assertFalse(exists);
	}
	
	@Then("^I get an error$")
	public void throwBankAccountError() {
		final Account account = this.getAccount();
		assertNull(account);
	}
	
}

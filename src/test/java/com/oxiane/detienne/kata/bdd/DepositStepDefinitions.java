package com.oxiane.detienne.kata.bdd;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.oxiane.detienne.kata.cucumber.Account;
import com.oxiane.detienne.kata.cucumber.AccountRepository;
import com.oxiane.detienne.kata.cucumber.BankingTransaction;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DepositStepDefinitions {

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

	private BankingTransaction bankingTransaction;

	public BankingTransaction getBankingTransaction() {
		return bankingTransaction;
	}

	public void setBankingTransaction(BankingTransaction bankingTransaction) {
		this.bankingTransaction = bankingTransaction;
	}

	private int expectedBankingTransactionsSize;

	public int getExpectedBankingTransactionsSize() {
		return expectedBankingTransactionsSize;
	}

	public void setExpectedBankingTransactionsSize(int expectedBankingTransactionsSize) {
		this.expectedBankingTransactionsSize = expectedBankingTransactionsSize;
	}

	private Double expectedBalance;

	public Double getExpectedBalance() {
		return expectedBalance;
	}

	public void setExpectedBalance(Double expectedBalance) {
		this.expectedBalance = expectedBalance;
	}

	@When("^I deposit (\\d+\\.\\d+) on my bank account (\\d+)$")
	public void deposit(Double amount, Long id) {
		// get initial values
		final Account account = this.getAccountRepository().read(id);
		this.setAccount(account);
		this.expectedBalance = Double.sum(account.getBalance(), amount);
		this.expectedBankingTransactionsSize = this.account.getBankingTransactions().size() + 1;
		// action
		this.setBankingTransaction(this.getAccountRepository().deposit(id, amount));
	}

	@Then("^Bank account (\\d+) is updated with a deposit operation$")
	public void checkBankAccountUpdate(Long id) {
		final Account account = this.getAccount();
		System.out.println(account);
		assertNotNull(account);
		assertEquals(account.getId(), id);
		// balance
		assertEquals(expectedBalance, account.getBalance());
		// banking transaction list
		assertEquals(expectedBankingTransactionsSize, account.getBankingTransactions().size());
		// last banking transaction
		final BankingTransaction lastBankingTransaction = account.getBankingTransactions()
				.get(account.getBankingTransactions().size() - 1);
		assertNotNull(lastBankingTransaction);
		assertNotNull(lastBankingTransaction.getId());
		assertNotNull(lastBankingTransaction.getDate());
		assertEquals("deposit", lastBankingTransaction.getType());
		assertEquals(this.bankingTransaction.getAmount(), lastBankingTransaction.getAmount());
	}

}

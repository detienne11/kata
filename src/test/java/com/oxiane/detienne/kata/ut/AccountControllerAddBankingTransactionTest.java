package com.oxiane.detienne.kata.ut;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.oxiane.detienne.kata.controller.AccountController;
import com.oxiane.detienne.kata.exception.AccountClosedException;
import com.oxiane.detienne.kata.exception.AccountNotFoundException;
import com.oxiane.detienne.kata.exception.IllegalBankingTransactionTypeException;
import com.oxiane.detienne.kata.model.BankingTransactionDTO;
import com.oxiane.detienne.kata.service.AccountService;

@RunWith(MockitoJUnitRunner.class)
public class AccountControllerAddBankingTransactionTest {

	private static final Long ACCOUNT_ID = 1001110099l;

	private static final Long ACCOUNT2_ID = 40044400011l;

	private static final Long CLOSED_ACCOUNT_ID = 20022200066l;

	private static final Long INVALID_ACCOUNT_ID = 66600066600l;

	@Mock
	private AccountService accountServiceMock;

	@InjectMocks
	private AccountController accountController;

	private BankingTransactionDTO bankingTransactionDTO;

	@Before
	public void init() {

		// inputs / output initialization
		bankingTransactionDTO = new BankingTransactionDTO();
		bankingTransactionDTO.setType("deposit");
		bankingTransactionDTO.setAmount(100.77d);

		// mock initialization

	}

	@DisplayName("Add a banking transaction")
	@Test
	public void testAddBankingTransaction_SN1() {

		// mock dependency
		Mockito.doNothing().when(accountServiceMock).addBankingTransaction(ACCOUNT_ID, bankingTransactionDTO);

		// execute service
		accountController.addBankingTransaction(ACCOUNT_ID, bankingTransactionDTO);
	}

	@DisplayName("Add a banking transaction with illegal type")
	@Test(expected = IllegalBankingTransactionTypeException.class)
	public void testAddBankingTransaction_SE1() {

		// override default values
		bankingTransactionDTO.setType("payment");

		// mock dependency
		Mockito.doThrow(new IllegalBankingTransactionTypeException(bankingTransactionDTO.getType()))
				.when(accountServiceMock).addBankingTransaction(ACCOUNT2_ID, bankingTransactionDTO);

		// execute service
		accountController.addBankingTransaction(ACCOUNT2_ID, bankingTransactionDTO);
	}

	@DisplayName("Add a banking transaction on an invalid bank account")
	@Test(expected = AccountNotFoundException.class)
	public void testAddBankingTransaction_SE2() {

		// mock dependency
		Mockito.doThrow(new AccountNotFoundException(INVALID_ACCOUNT_ID)).when(accountServiceMock)
				.addBankingTransaction(INVALID_ACCOUNT_ID, bankingTransactionDTO);

		// execute service
		accountController.addBankingTransaction(INVALID_ACCOUNT_ID, bankingTransactionDTO);
	}

	@DisplayName("Add a banking transaction on a closed bank account")
	@Test(expected = AccountClosedException.class)
	public void testAddBankingTransaction_SE3() {

		// mock dependency
		Mockito.doThrow(new AccountClosedException(CLOSED_ACCOUNT_ID)).when(accountServiceMock)
				.addBankingTransaction(CLOSED_ACCOUNT_ID, bankingTransactionDTO);

		// execute service
		accountController.addBankingTransaction(CLOSED_ACCOUNT_ID, bankingTransactionDTO);
	}

}

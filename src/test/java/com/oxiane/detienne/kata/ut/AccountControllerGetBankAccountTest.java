package com.oxiane.detienne.kata.ut;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.oxiane.detienne.kata.controller.AccountController;
import com.oxiane.detienne.kata.exception.AccountNotFoundException;
import com.oxiane.detienne.kata.model.AccountDTO;
import com.oxiane.detienne.kata.service.AccountService;

@RunWith(MockitoJUnitRunner.class)
public class AccountControllerGetBankAccountTest {

	private static final Long ACCOUNT_ID = 1001110099l;

	@Mock
	private AccountService accountServiceMock;

	@InjectMocks
	private AccountController accountController;

	private AccountDTO acccountMock;

	@Before
	public void init() {

		// inputs / output initialization

		// mock initialization
		this.acccountMock = new AccountDTO();
		this.acccountMock.setId(ACCOUNT_ID);
		this.acccountMock.setActive(true);
		this.acccountMock.setBankingTransactions(new ArrayList<>());

	}

	@DisplayName("Retrieve a valid Bank account")
	@Test
	public void testGetBankAccount_SN1() {

		// mock dependency
		Mockito.doReturn(acccountMock).when(accountServiceMock).findById(Mockito.anyLong());

		// execute service
		final AccountDTO account = accountController.getBankAccount(ACCOUNT_ID);

		// test result
		Assert.assertNotNull(account);
		Assert.assertEquals(acccountMock.getId(), account.getId());
		Assert.assertEquals(acccountMock.getActive(), account.getActive());
		Assert.assertEquals(acccountMock.getBankingTransactions().size(), account.getBankingTransactions().size());
	}

	@DisplayName("Retrieve a invalid Bank account")
	@Test(expected = AccountNotFoundException.class)
	public void testGetBankAccount_SE1() {

		// mock dependency
		Mockito.doThrow(new AccountNotFoundException(ACCOUNT_ID)).when(accountServiceMock).findById(Mockito.anyLong());

		// execute service
		accountController.getBankAccount(ACCOUNT_ID);
	}

}

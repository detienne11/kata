package com.oxiane.detienne.kata.ut;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
import com.oxiane.detienne.kata.model.BankingTransactionDTO;
import com.oxiane.detienne.kata.service.AccountService;

@RunWith(MockitoJUnitRunner.class)
public class AccountControllerGetBankingTransactionsTest {

	private static final Long ACCOUNT_ID = 1001110099l;

	@Mock
	private AccountService accountServiceMock;

	@InjectMocks
	private AccountController accountController;

	// inputs / output
	private List<BankingTransactionDTO> bankingTransactionsMock;

	@Before
	public void init() {

		// inputs / output initialization

		// mock initialization
		bankingTransactionsMock = new ArrayList<>();
		BankingTransactionDTO bt = new BankingTransactionDTO();
		bt.setId(1l);
		bt.setAmount(1000d);
		bt.setDate(LocalDateTime.of(2021, 01, 10, 12, 0, 0, 0));
		bt.setType("deposit");
		bankingTransactionsMock.add(bt);

		bt = new BankingTransactionDTO();
		bt.setId(2l);
		bt.setAmount(40.53d);
		bt.setDate(LocalDateTime.of(2021, 01, 12, 18, 47, 0, 69));
		bt.setType("deposit");
		bankingTransactionsMock.add(bt);

		bt = new BankingTransactionDTO();
		bt.setId(3l);
		bt.setAmount(40.00d);
		bt.setDate(LocalDateTime.of(2021, 01, 13, 9, 30, 52, 00));
		bt.setType("withdraw");
		bankingTransactionsMock.add(bt);

	}

	@DisplayName("Retrieve detail of a valid Bank account")
	@Test
	public void testGetBankingTransactions_SN1() {

		// mock dependency
		Mockito.doReturn(bankingTransactionsMock).when(accountServiceMock).findByAccount(Mockito.anyLong(),
				Mockito.anyInt(), Mockito.anyInt());

		// execute service
		final List<BankingTransactionDTO> result = accountController.getBankingTransactions(ACCOUNT_ID, 0, 2);

		// test result
		Assert.assertNotNull(result);
		Assert.assertEquals(bankingTransactionsMock.size(), result.size());
		for (int i = 0; i < bankingTransactionsMock.size(); i++) {
			final BankingTransactionDTO expected = result.get(i);
			final BankingTransactionDTO actual = result.get(i);
			Assert.assertEquals(expected.getId(), actual.getId());
			Assert.assertEquals(expected.getType(), actual.getType());
			Assert.assertEquals(expected.getAmount(), actual.getAmount());
			Assert.assertEquals(expected.getDate(), actual.getDate());
		}

	}

	@DisplayName("Retrieve detail a invalid Bank account")
	@Test(expected = AccountNotFoundException.class)
	public void testGetBankingTransactions_SE1() {

		// mock dependency
		Mockito.doThrow(new AccountNotFoundException(ACCOUNT_ID)).when(accountServiceMock)
				.findByAccount(Mockito.anyLong(), Mockito.anyInt(), Mockito.anyInt());

		// execute service
		accountController.getBankingTransactions(ACCOUNT_ID, 0, 6);
	}

}

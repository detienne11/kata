package com.oxiane.detienne.kata.tu;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oxiane.detienne.kata.entity.Account;
import com.oxiane.detienne.kata.entity.BankingTransaction;
import com.oxiane.detienne.kata.exception.AccountNotFoundException;
import com.oxiane.detienne.kata.model.AccountDTO;
import com.oxiane.detienne.kata.model.BankingTransactionDTO;
import com.oxiane.detienne.kata.repository.AccountDao;
import com.oxiane.detienne.kata.service.AccountServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceFindByIdTest {

	private static final Logger logger = LoggerFactory.getLogger(AccountServiceFindByIdTest.class);

	private static final Long ACCOUNT_ID = 1001110099l;

	@Mock
	private AccountDao accountDaoMock;

	@InjectMocks
	private AccountServiceImpl accountService;

	private Account acccountMock;

	@Before
	public void init() {

		// inputs / output initialization

		// mock initialization
		acccountMock = new Account();
		acccountMock.setId(ACCOUNT_ID);
		acccountMock.setActive(true);

		BankingTransaction bt = new BankingTransaction();
		bt.setId(1l);
		bt.setAccount(acccountMock);
		bt.setAmount(1000d);
		bt.setDate(LocalDateTime.of(2021, 01, 10, 12, 0, 0, 0));
		bt.setType("deposit");
		acccountMock.getBankingTransactions().add(bt);

		bt = new BankingTransaction();
		bt.setId(2l);
		bt.setAccount(acccountMock);
		bt.setAmount(40.53d);
		bt.setDate(LocalDateTime.of(2021, 01, 12, 18, 47, 0, 69));
		bt.setType("deposit");
		acccountMock.getBankingTransactions().add(bt);

		bt = new BankingTransaction();
		bt.setId(3l);
		bt.setAccount(acccountMock);
		bt.setAmount(40.00d);
		bt.setDate(LocalDateTime.of(2021, 01, 13, 9, 30, 52, 00));
		bt.setType("withdraw");
		acccountMock.getBankingTransactions().add(bt);
	}

	@Test
	public void findById_SN1() {
		logger.debug("Bank account valid");
		// mock dependency
		Mockito.doReturn(acccountMock).when(accountDaoMock).findById(Mockito.anyLong());

		// execute service
		final AccountDTO account = accountService.findById(ACCOUNT_ID);

		// test result
		Assert.assertNotNull(account);
		Assert.assertEquals(acccountMock.getId(), account.getId());
		Assert.assertEquals(acccountMock.isActive(), account.isActive());
		Assert.assertEquals(acccountMock.getBankingTransactions().size(), account.getBankingTransactions().size());
	}

	@Test(expected = AccountNotFoundException.class)
	public void findById_SE1() {
		logger.debug("Account not found");
		// mock dependency
		Mockito.doReturn(null).when(accountDaoMock).findById(Mockito.anyLong());

		// execute service
		accountService.findById(ACCOUNT_ID);
	}

}

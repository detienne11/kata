package com.oxiane.detienne.kata.ut;

import java.time.LocalDateTime;
import java.util.Optional;

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
import com.oxiane.detienne.kata.repository.AccountRepository;
import com.oxiane.detienne.kata.service.AccountServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceFindByAccountTest {

	private static final Logger logger = LoggerFactory.getLogger(AccountServiceFindByAccountTest.class);

	private static final Long ACCOUNT_ID = 1001110099l;

	@Mock
	private AccountRepository accountDaoMock;

	@InjectMocks
	private AccountServiceImpl accountService;

	private Account acccountMock;

	@Before
	public void init() {

		// inputs / output initialization

		// mock initialization
		acccountMock = new Account();
		acccountMock.setId(ACCOUNT_ID);
		acccountMock.setBalance(1000.00d);
		acccountMock.setActive(true);
		
	}

	@Test
	public void findById_SN1() {
		logger.debug("Bank account valid");
		// mock dependency
		Mockito.doReturn(Optional.of(acccountMock)).when(accountDaoMock).findById(Mockito.anyLong());

		// execute service
		final AccountDTO account = accountService.findById(ACCOUNT_ID);

		// test result
		Assert.assertNotNull(account);
		Assert.assertEquals(acccountMock.getId(), account.getId());
		Assert.assertEquals(acccountMock.getBalance(), account.getBalance());
		Assert.assertEquals(acccountMock.getActive(), account.getActive());
	}

	@Test(expected = AccountNotFoundException.class)
	public void findById_SE1() {
		logger.debug("Account not found");
		// mock dependency
		Mockito.doReturn(Optional.empty()).when(accountDaoMock).findById(Mockito.anyLong());

		// execute service
		accountService.findById(ACCOUNT_ID);
	}

}

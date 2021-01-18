package com.oxiane.detienne.kata.ut;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.oxiane.detienne.kata.entity.Account;
import com.oxiane.detienne.kata.exception.AccountNotFoundException;
import com.oxiane.detienne.kata.model.AccountDTO;
import com.oxiane.detienne.kata.repository.AccountRepository;
import com.oxiane.detienne.kata.service.AccountServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceFindByIdTest {

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
		acccountMock.setActive(true);
		acccountMock.setBankingTransactions(new ArrayList<>());
	}

	@DisplayName("Retrieve a valid banking account")
	@Test
	public void testFindById_SN1() {

		// mock dependency
		Mockito.doReturn(Optional.of(acccountMock)).when(accountDaoMock).findById(Mockito.anyLong());

		// execute service
		final AccountDTO account = accountService.findById(ACCOUNT_ID);

		// test result
		Assert.assertNotNull(account);
		Assert.assertEquals(acccountMock.getId(), account.getId());
		Assert.assertEquals(acccountMock.getActive(), account.getActive());
		Assert.assertEquals(acccountMock.getBankingTransactions().size(), account.getBankingTransactions().size());
	}

	@DisplayName("Retrieve a invalid banking account")
	@Test(expected = AccountNotFoundException.class)
	public void testFindById_SE1() {

		// mock dependency
		Mockito.doReturn(Optional.empty()).when(accountDaoMock).findById(Mockito.anyLong());

		// execute service
		accountService.findById(ACCOUNT_ID);
	}

}

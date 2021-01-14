package com.oxiane.detienne.kata.tu;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oxiane.detienne.kata.dto.BankingTransactionDTO;
import com.oxiane.detienne.kata.exception.AccountClosedException;
import com.oxiane.detienne.kata.exception.AccountNotFoundException;
import com.oxiane.detienne.kata.exception.IllegalBankingTransactionTypeException;
import com.oxiane.detienne.kata.model.Account;
import com.oxiane.detienne.kata.model.BankingTransaction;
import com.oxiane.detienne.kata.repository.AccountDao;
import com.oxiane.detienne.kata.service.AccountServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceAddBankingTransactionTest {

	private static final Logger logger = LoggerFactory.getLogger(AccountServiceAddBankingTransactionTest.class);

	private static final Long ACCOUNT_ID = 1001110099l;

	@Mock
	private AccountDao accountDaoMock;

	@InjectMocks
	private AccountServiceImpl accountService;

	private Account acccountMock;

	private BankingTransactionDTO bankingTransactionDTO;

	@Before
	public void init() {

		// inputs / output initialization
		bankingTransactionDTO = new BankingTransactionDTO();
		bankingTransactionDTO.setType("deposit");
		bankingTransactionDTO.setAmount(100.77d);
		
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

	@Test(expected = IllegalBankingTransactionTypeException.class)
	public void addBankingTransaction_SE1() {
		logger.debug("Illegal Banking Transaction Type");
		// override default values
		bankingTransactionDTO.setType("payment");

		// execute service
		accountService.addBankingTransaction(ACCOUNT_ID, bankingTransactionDTO);
	}

	@Test(expected = AccountNotFoundException.class)
	public void addBankingTransaction_SE2() {
		logger.debug("Account not found");
		// mock dependency
		Mockito.doReturn(null).when(accountDaoMock).findById(Mockito.anyLong());

		// execute service
		accountService.addBankingTransaction(ACCOUNT_ID, bankingTransactionDTO);
	}

	@Test(expected = AccountClosedException.class)
	public void addBankingTransaction_SE3() {
		logger.debug("Account closed");
		// override default values
		acccountMock.setActive(false);
		// mock dependency
		Mockito.doReturn(acccountMock).when(accountDaoMock).findById(Mockito.anyLong());

		// execute service
		accountService.addBankingTransaction(ACCOUNT_ID, bankingTransactionDTO);
	}

}

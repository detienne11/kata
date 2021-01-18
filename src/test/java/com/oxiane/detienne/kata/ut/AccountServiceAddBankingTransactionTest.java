package com.oxiane.detienne.kata.ut;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.oxiane.detienne.kata.entity.Account;
import com.oxiane.detienne.kata.entity.BankingTransaction;
import com.oxiane.detienne.kata.exception.AccountClosedException;
import com.oxiane.detienne.kata.exception.AccountNotFoundException;
import com.oxiane.detienne.kata.exception.IllegalBankingTransactionTypeException;
import com.oxiane.detienne.kata.model.BankingTransactionDTO;
import com.oxiane.detienne.kata.repository.AccountRepository;
import com.oxiane.detienne.kata.service.AccountServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceAddBankingTransactionTest {

	private static final Long ACCOUNT_ID = 1001110099l;

	@Mock
	private AccountRepository accountDaoMock;

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

	@DisplayName("Add a banking transaction with illegal type")
	@Test(expected = IllegalBankingTransactionTypeException.class)
	public void testAddBankingTransaction_SE1() {

		// override default values
		bankingTransactionDTO.setType("payment");

		// execute service
		accountService.addBankingTransaction(ACCOUNT_ID, bankingTransactionDTO);
	}

	@DisplayName("Add a banking transaction on an invalid bank account")
	@Test(expected = AccountNotFoundException.class)
	public void testAddBankingTransaction_SE2() {

		// mock dependency
		Mockito.doReturn(Optional.empty()).when(accountDaoMock).findById(Mockito.anyLong());

		// execute service
		accountService.addBankingTransaction(ACCOUNT_ID, bankingTransactionDTO);
	}

	@DisplayName("Add a banking transaction on a closed bank account")
	@Test(expected = AccountClosedException.class)
	public void testAddBankingTransaction_SE3() {

		// override default values
		acccountMock.setActive(false);
		// mock dependency
		Mockito.doReturn(Optional.of(acccountMock)).when(accountDaoMock).findById(Mockito.anyLong());

		// execute service
		accountService.addBankingTransaction(ACCOUNT_ID, bankingTransactionDTO);
	}

}

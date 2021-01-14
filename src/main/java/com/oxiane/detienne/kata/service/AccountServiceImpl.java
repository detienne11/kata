package com.oxiane.detienne.kata.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oxiane.detienne.kata.dto.AccountDTO;
import com.oxiane.detienne.kata.dto.BankingTransactionDTO;
import com.oxiane.detienne.kata.repository.AccountDao;

@Service
public class AccountServiceImpl implements AccountService {

	private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

	@Autowired
	private AccountDao accountDao;

	public AccountDao getAccountDao() {
		return accountDao;
	}

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	@Override
	public AccountDTO findById(Long id) {
		throw new RuntimeException("Not implemented");
	}

	@Override
	public void addBankingTransaction(Long id, BankingTransactionDTO bankingTransactionDTO) {
		throw new RuntimeException("Not implemented");
	}

}

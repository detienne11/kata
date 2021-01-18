package com.oxiane.detienne.kata.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oxiane.detienne.kata.entity.Account;
import com.oxiane.detienne.kata.entity.BankingTransaction;

@Repository
public class AccountDaoImpl implements AccountDao {

	private static final Logger logger = LoggerFactory.getLogger(AccountDaoImpl.class);

	@Autowired
	private EntityManager entityManager;

	@Override
	public Account findById(Long id) {
		final Account account = entityManager.find(Account.class, id);
		return account;
	}

	@Override
	@Transactional
	public void save(BankingTransaction entity) {
		logger.debug("BankingTransaction {}", entity);
		entityManager.persist(entity);
	}

}

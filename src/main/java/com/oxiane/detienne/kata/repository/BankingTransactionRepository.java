package com.oxiane.detienne.kata.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oxiane.detienne.kata.entity.Account;
import com.oxiane.detienne.kata.entity.BankingTransaction;

@Repository
public interface BankingTransactionRepository extends JpaRepository<BankingTransaction, Long> {

	/**
	 * Find bank transactions by account
	 * 
	 * @param id
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<BankingTransaction> findByAccount(Account account, Pageable page);

}

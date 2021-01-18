package com.oxiane.detienne.kata.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.oxiane.detienne.kata.entity.Account;
import com.oxiane.detienne.kata.entity.BankingTransaction;
import com.oxiane.detienne.kata.exception.AccountClosedException;
import com.oxiane.detienne.kata.exception.AccountNotFoundException;
import com.oxiane.detienne.kata.exception.IllegalBankingTransactionTypeException;
import com.oxiane.detienne.kata.model.AccountDTO;
import com.oxiane.detienne.kata.model.BankingTransactionDTO;
import com.oxiane.detienne.kata.repository.AccountRepository;
import com.oxiane.detienne.kata.repository.BankingTransactionRepository;

@Service
public class AccountServiceImpl implements AccountService {

	private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

	private static final String DATE_FIELD = "date";

	private static final String WITHDRAW_TRANSACTION_TYPE = "withdraw";

	private static final String DEPOSIT_TRANSACTION_TYPE = "deposit";

	@Autowired
	private AccountRepository accountRepository;

	public AccountRepository getAccountRepository() {
		return accountRepository;
	}

	public void setAccountRepository(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Autowired
	private BankingTransactionRepository bankingTransactionRepository;

	public BankingTransactionRepository getBankingTransactionRepository() {
		return bankingTransactionRepository;
	}

	public void setBankingTransactionRepository(BankingTransactionRepository bankingTransactionRepository) {
		this.bankingTransactionRepository = bankingTransactionRepository;
	}

	/*
	 * Convert Account entity to DTO
	 */
	private AccountDTO toDTO(Account entity) {
		final AccountDTO dto = new AccountDTO();
		dto.setId(entity.getId());
		dto.setActive(entity.getActive());
		dto.setBalance(entity.getBalance());
		dto.setBankingTransactions(new ArrayList<>());
		logger.debug("Convert entity to DTO : Account\nentity:{}\ndto:{}", entity, dto);
		return dto;
	}

	/*
	 * Convert BankingTransaction entities List to DTO List
	 */
	private List<BankingTransactionDTO> toDTO(List<BankingTransaction> entities) {
		List<BankingTransactionDTO> dtos = null;
		if (entities != null) {
			dtos = entities.stream().map(it -> this.toDTO(it)).collect(Collectors.toList());
		}
		return dtos;
	}

	/*
	 * Convert BankingTransaction entity to DTO
	 */
	private BankingTransactionDTO toDTO(BankingTransaction entity) {
		BankingTransactionDTO dto = new BankingTransactionDTO();
		dto.setId(entity.getId());
		dto.setType(entity.getType());
		dto.setDate(entity.getDate());
		dto.setAmount(entity.getAmount());
		return dto;
	}

	/*
	 * Convert Account DTO to entity
	 */
	private Account toEntity(AccountDTO dto) {
		final Account entity = new Account();
		entity.setId(dto.getId());
		entity.setActive(dto.getActive());
		entity.setBalance(dto.getBalance());
		logger.debug("Convert DTO to entity : Account\ndto:{}\nentity:{}", dto, entity);
		return entity;
	}

	/*
	 * Convert BankingTransaction DTO to entity
	 */
	private BankingTransaction toEntity(BankingTransactionDTO dto) {
		BankingTransaction entity = new BankingTransaction();
		entity.setId(dto.getId());
		entity.setType(dto.getType());
		entity.setDate(dto.getDate());
		entity.setAmount(dto.getAmount());
		logger.debug("Convert DTO to entity : BankingTransaction\ndto:{}\nentity:{}", dto, entity);
		return entity;
	}

	@Override
	public AccountDTO findById(Long id) {
		final Account entity = this.getAccountRepository().findById(id)
				.orElseThrow(() -> new AccountNotFoundException(id));
		return this.toDTO(entity);
	}

	@Override
	public List<BankingTransactionDTO> findByAccount(Long id, int page, int size) {

		// Retrieve bank account
		final Account account = this.toEntity(this.findById(id));

		// Retrieve page of banking transactions
		final Pageable paging = PageRequest.of(page, size, Sort.by(Direction.DESC, AccountServiceImpl.DATE_FIELD));
		final Page<BankingTransaction> results = this.getBankingTransactionRepository().findByAccount(account, paging);

		List<BankingTransactionDTO> dtos = null;
		if (results.hasContent()) {
			dtos = this.toDTO(results.getContent());
		} else {
			dtos = new ArrayList<BankingTransactionDTO>();
		}
		return dtos;
	}

	@Override
	@Transactional
	public void addBankingTransaction(Long id, BankingTransactionDTO bankingTransactionDTO) {

		// check inputs
		if (bankingTransactionDTO != null && bankingTransactionDTO.getType() != null
				&& !bankingTransactionDTO.getType().matches("^(" + AccountServiceImpl.DEPOSIT_TRANSACTION_TYPE + "|"
						+ AccountServiceImpl.WITHDRAW_TRANSACTION_TYPE + ")$")) {
			throw new IllegalBankingTransactionTypeException(bankingTransactionDTO.getType());
		}

		final Account accountEntity = this.toEntity(this.findById(id));

		// check rules
		if (Boolean.FALSE.equals(accountEntity.getActive())) {
			throw new AccountClosedException(id);
		}

		// persist datas
		final BankingTransaction bankingTransactionEntity = this.toEntity(bankingTransactionDTO);
		bankingTransactionEntity.setAccount(accountEntity);
		bankingTransactionEntity.setDate(LocalDateTime.now());
		// add banking transaction
		this.getBankingTransactionRepository().save(bankingTransactionEntity);
		// update balance bank account
		final Double balance = (accountEntity.getBalance() != null) ? accountEntity.getBalance() : 0;
		final Double amount = (bankingTransactionEntity.getAmount() != null) ? bankingTransactionEntity.getAmount() : 0;
		final Double value = (AccountServiceImpl.WITHDRAW_TRANSACTION_TYPE.equals(bankingTransactionEntity.getType()))
				? balance - amount
				: balance + amount;
		accountEntity.setBalance(value);
		this.getAccountRepository().save(accountEntity);
	}

}

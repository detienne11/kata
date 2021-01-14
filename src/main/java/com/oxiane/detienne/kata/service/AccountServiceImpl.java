package com.oxiane.detienne.kata.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oxiane.detienne.kata.dto.AccountDTO;
import com.oxiane.detienne.kata.dto.BankingTransactionDTO;
import com.oxiane.detienne.kata.exception.AccountClosedException;
import com.oxiane.detienne.kata.exception.AccountNotFoundException;
import com.oxiane.detienne.kata.exception.IllegalBankingTransactionTypeException;
import com.oxiane.detienne.kata.model.Account;
import com.oxiane.detienne.kata.model.BankingTransaction;
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

	/*
	 * Convert entity to DTO : Account
	 */
	private AccountDTO toDTO(Account entity) {
		final AccountDTO dto = new AccountDTO();
		dto.setId(entity.getId());
		dto.setActive(entity.isActive());
		dto.setId(entity.getId());
		dto.setBankingTransactions(this.toDTO(entity.getBankingTransactions()));
		logger.debug("Convert entity to DTO : Account\nentity:{}\ndto:{}", entity, dto);
		return dto;
	}

	/*
	 * Convert List of entities to DTOs : BankingTransaction
	 */
	private List<BankingTransactionDTO> toDTO(List<BankingTransaction> entities) {
		List<BankingTransactionDTO> dtos = null;
		if (entities != null) {
			dtos = entities.stream().map(it -> this.toDTO(it)).collect(Collectors.toList());
		}
		return dtos;
	}

	/*
	 * Convert entity to DTO : BankingTransaction
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
	 * Convert DTO to entity : BankingTransaction
	 */
	private BankingTransaction toDAO(BankingTransactionDTO dto) {
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
		final Account dao = accountDao.findById(id);

		// check rules
		if (dao == null) {
			throw new AccountNotFoundException(id);
		}

		// convert toDTO
		final AccountDTO dto = toDTO(dao);
		return dto;
	}

	@Override
	public void addBankingTransaction(Long id, BankingTransactionDTO bankingTransactionDTO) {

		// check inputs
		if (bankingTransactionDTO != null && bankingTransactionDTO.getType() != null
				&& !bankingTransactionDTO.getType().matches("^(deposit|withdraw)$")) {
			throw new IllegalBankingTransactionTypeException(bankingTransactionDTO.getType());
		}

		final Account account = accountDao.findById(id);

		// check rules
		if (account == null) {
			throw new AccountNotFoundException(id);
		} else if (!account.isActive()) {
			throw new AccountClosedException(id);
		}

		// persist datas
		final BankingTransaction dao = this.toDAO(bankingTransactionDTO);
		dao.setAccount(account);
		dao.setDate(LocalDateTime.now());
		accountDao.save(dao);
	}

}

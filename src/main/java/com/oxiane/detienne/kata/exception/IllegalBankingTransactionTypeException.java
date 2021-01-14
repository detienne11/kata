package com.oxiane.detienne.kata.exception;

public class IllegalBankingTransactionTypeException extends RuntimeException {

	public IllegalBankingTransactionTypeException(String type) {
		super(String.format("Illegal banking transaction type (type=%s), only expected [deposit, withdraw]", type));
	}
}

package com.oxiane.detienne.kata.exception;

public class AccountClosedException extends RuntimeException {

	public AccountClosedException(Long id) {
		super(String.format("Bank account closed (id=%d), no banking transactions are allowed", id));
	}
}

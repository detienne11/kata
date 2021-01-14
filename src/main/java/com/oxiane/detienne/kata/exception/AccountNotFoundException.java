package com.oxiane.detienne.kata.exception;

public class AccountNotFoundException extends RuntimeException {

	public AccountNotFoundException(Long id) {
		super(String.format("Bank account not found (id=%d)", id));
	}
}

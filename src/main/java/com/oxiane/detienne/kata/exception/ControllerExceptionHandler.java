package com.oxiane.detienne.kata.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ControllerExceptionHandler {

	/*
	 * Manage AccountNotFoundException
	 */
	@ExceptionHandler(AccountNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ErrorMessage handleAccountNotFoundException(AccountNotFoundException ex, WebRequest request) {
		final ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND.value(), LocalDateTime.now(),
				ex.getMessage(), request.getDescription(false));
		return message;
	}

	/*
	 * Manage AccountClosedException
	 */
	@ExceptionHandler(AccountClosedException.class)
	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	public ErrorMessage handleAccountClosedException(AccountClosedException ex, WebRequest request) {
		final ErrorMessage message = new ErrorMessage(HttpStatus.FORBIDDEN.value(), LocalDateTime.now(),
				ex.getMessage(), request.getDescription(false));
		return message;
	}

	/*
	 * Manage IllegalBankingTransactionTypeException
	 */
	@ExceptionHandler(IllegalBankingTransactionTypeException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorMessage handleIllegalBankingTransactionTypeException(IllegalBankingTransactionTypeException ex,
			WebRequest request) {
		final ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), LocalDateTime.now(),
				ex.getMessage(), request.getDescription(false));
		return message;
	}

}

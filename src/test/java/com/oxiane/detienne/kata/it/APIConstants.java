package com.oxiane.detienne.kata.it;

public interface APIConstants {

	public static final String API_ROOT_CONTEXT = "http://localhost:8081/api/v1";
	
	public static final String GET_ACCOUNTS_PATTERN = API_ROOT_CONTEXT + "/accounts/%s";
	
	public static final String POST_BANKINGTRANSACTIONS_PATTERN = API_ROOT_CONTEXT + "/accounts/%s/bankingtransactions";
	
	public static final String GET_BANKINGTRANSACTIONS_PATTERN = API_ROOT_CONTEXT + "/accounts/%s/bankingtransactions?page=%d&size=%d";
	
	public static final String WITHDRAW_TRANSACTION_TYPE = "withdraw";

	public static final String DEPOSIT_TRANSACTION_TYPE = "deposit";
}

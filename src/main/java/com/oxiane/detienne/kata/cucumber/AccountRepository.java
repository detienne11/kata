package com.oxiane.detienne.kata.cucumber;

import java.util.HashMap;
import java.util.Map;

/*
 * For Cucumber Tests
 */
public class AccountRepository {

	private static Map<Long, Account> datas = null;
	 
    public AccountRepository() {
        datas = new HashMap<>();
        datas.put(1L, new Account(1L, true));
        datas.put(3L, new Account(3L, false));
    } 
   
    public boolean checkAccountExistence(Long id) {
    	return datas.containsKey(id);
    }
    
    public Account read(Long id) {
        return datas.get(id);
    }
    
	public BankingTransaction withdraw(Long accountid, Double amount) {
		BankingTransaction bankingTransaction = new BankingTransaction("withdraw", amount);
		datas.get(accountid).getBankingTransactions().add(bankingTransaction);
		return bankingTransaction;
	}
	
	public BankingTransaction deposit(Long accountid, Double amount) {
		BankingTransaction bankingTransaction = new BankingTransaction("deposit", amount);
		datas.get(accountid).getBankingTransactions().add(bankingTransaction);
		return bankingTransaction;
	}
	
}

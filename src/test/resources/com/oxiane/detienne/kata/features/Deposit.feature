@DepositFeature
Feature: Deposit functionality
	In order to save money
	As a bank client
	I want to make a deposit in my account

  Scenario Outline: Deposit on a bank account
    Given Bank account <accountid> exists
    When I deposit <amount> on my bank account <accountid>
    Then Bank account <accountid> is updated with a deposit operation
  
  Examples:
    | accountid 	| amount |
    | 1 					| 100.00 |
    | 3 					| 150.56 |

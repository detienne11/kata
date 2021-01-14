@WithdrawalFeature
Feature: Withdrawal functionality
	In order to retrieve some or all of my savings
	As a bank client
	I want to make a withdrawal from my account

  Scenario Outline: Withdrawal on a bank account
    Given Bank account <accountid> exists
    When I withdraw <amount> on my bank account <accountid>
    Then Bank account <accountid> is updated with a withdraw operation
  
  Examples:
    | accountid 	| amount |
    | 1 					| 20.00 |
    | 3 					| 100.00 |

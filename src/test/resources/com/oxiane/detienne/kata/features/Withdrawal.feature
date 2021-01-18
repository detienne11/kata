@e2e @WithdrawalFeature
Feature: Withdrawal functionality
	In order to retrieve some or all of my savings
	As a bank client
	I want to make a withdrawal from my account

  Scenario Outline: Withdrawal on a bank account 
    Given Bank account <accountid> exists for withdraw operation
    When I withdraw <amount> on my bank account <accountid>
    Then Bank account <accountid> is updated with a withdraw operation
  
  Examples:
    | accountid 	| amount	|
    | 10011100099	| 20.00		|
    | 40044400011	| 250.00	|

  Scenario Outline: Withdrawal on a closed bank account
    Given Bank account <accountid> exists for withdraw operation
    When I withdraw <amount> on my bank account <accountid>
    Then I get an account closed error for withdraw operation

  Examples:
    | accountid 	| amount |
    | 20022200066	| 60.00 |

  Scenario Outline: Withdrawal on an invalid bank account
    Given Bank account <accountid> not exists for withdraw operation
    When I withdraw <amount> on my bank account <accountid>
    Then I get an account not found error for withdraw operation
  
  Examples:
    | accountid 	| amount |
    | 66600066600	| 80.00 |      
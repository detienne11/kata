@e2e @DepositFeature
Feature: Deposit functionality
	In order to save money
	As a bank client
	I want to make a deposit in my account

  Scenario Outline: Deposit on a bank account
    Given Bank account <accountid> exists for deposit operation
    When I deposit <amount> on my bank account <accountid>
    Then Bank account <accountid> is updated with a deposit operation
  
  Examples:
    | accountid 	| amount |
    | 10011100099	| 100.00 |
    | 40044400011	| 150.56 |

  Scenario Outline: Deposit on a closed bank account
    Given Bank account <accountid> exists for deposit operation
    When I deposit <amount> on my bank account <accountid>
    Then I get an account closed error for deposit operation

  Examples:
    | accountid 	| amount |
    | 20022200066	| 500.00 |

  Scenario Outline: Deposit on an invalid bank account
    Given Bank account <accountid> not exists for deposit operation
    When I deposit <amount> on my bank account <accountid>
    Then I get an account not found error for deposit operation
  
  Examples:
    | accountid 	| amount |
    | 66600066600	| 400.00 |
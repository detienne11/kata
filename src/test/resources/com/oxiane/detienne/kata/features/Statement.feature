@e2e @StatementFeature
Feature: Retrieve balance and history of a bank account
	In order to check my operations
	As a bank client
	I want to see the history (operation, date, amount, balance) of my operations

  Scenario Outline: Retrieve a bank account detail
    Given Bank account <accountid> exists
    When I ask for bank account detail
    Then I get detail of bank account <accountid> : operations, date, amount, balance 
  
  Examples:
    | accountid		|
    | 10011100099	|
    | 40044400011 |
      
  Scenario Outline: Retrieve an invalid bank account detail
    Given Bank account <accountid> not exists
    When I ask for bank account detail
    Then I get an account not found error
  
  Examples:
    | accountid		|
    | 66600066600 |
    
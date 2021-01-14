@StatementFeature
Feature: Retrieve balance and history of a bank account
	In order to check my operations
	As a bank client
	I want to see the history (operation, date, amount, balance) of my operations

  Scenario Outline: Retrieve a bank account detail
    Given Bank account <accountid> exists
    When I ask for detail of bank account <accountid>
    Then I get detail of bank account <accountid> : operations, date, amount, balance 
  
  Examples:
    | accountid |
    | 1 |
    | 3 |
      
  Scenario Outline: Retrieve an invalid bank account detail
    Given Bank account <accountid> not exists
    When I ask for detail of bank account <accountid>
    Then I get an error
  
  Examples:
    | accountid |
    | 2 |
    
### Kata application ###

@author : David ETIENNE (detienne@oxiane.com)

### Requirements ###

* JDK 15
* Maven 3.6.3
* Git 2.28.0
* Postman 7.36.1

### Up and Run Application ###
> Clone application in commandline :
>>* git clone https://github.com/detienne11/kata.git
>>* cd kata

> To run application in commandline :
>>* mvn spring-boot:run

Application running on localhost:8081

### Specifications - Bank account kata ###

Think of your personal bank account experience When in doubt, go for the simplest solution

##### Requirements

* Deposit and Withdrawal
* Account statement (date, amount, balance)
* Statement printing

##### User Stories

>* US 1:
>>In order to save money
>>As a bank client
>>I want to make a deposit in my account

>* US 2:
>>In order to retrieve some or all of my savings
>>As a bank client
>>I want to make a withdrawal from my account

>* US 3:
>>In order to check my operations
>>As a bank client
>>I want to see the history (operation, date, amount, balance) of my operations

### API ###

> After Application running, Swagger Definition is available at http://localhost:8081/swagger-ui/

### Integration Tests with Cucumber ###

> Features are available in 
>> ./src/test/resources/com/oxiane/detienne/kata/features

> StepDefinitions are available in
>> ./src/test/java/com/oxiane/detienne/kata/bdd

> To run Integration tests in commandline (Application must not already running): 
>>* Only @StatementFeature : mvn verify -Pfailsafe -Dcucumber.filter.tags=@StatementFeature
>>* Only @DepositFeature : mvn verify -Pfailsafe -Dcucumber.filter.tags=@DepositFeature
>>* Only @WithdrawalFeature : mvn verify -Pfailsafe -Dcucumber.filter.tags=@WithdrawalFeature
>>* All Features : mvn verify -Pfailsafe

### E2E tests with Postman ###

> Launch Postman application

> Import Collection from file :
>> ./src/test/e2e/kata.postman_collection.json

> Run whole collection or just a specific test

### Unit Tests with JUnit ###

> Implementation are available in
>> ./src/test/java/com/oxiane/detienne/kata/tu

> To run Unit tests in commandline : 
>>* mvn clean test

### Database Schema ###

#### accounts table
| Field	 	| Type 				| Description								|
| ---------	| -----------------	| -----------------------------------------	|
| id		| bigint not null 	| account identifier (PK)					|
| active 	| boolean not null	| account state (false: close, true: open)	|
| balance	| double not null 	| balance               					|

#### banking_transactions table
| Field	 		| Type 					| Description							|
| -------------	| ---------------------	| -------------------------------------	|
| id  			| bigint not null 		| banking transactions identifier (PK)	|
| account_id	| bigint not null		| account identifier (FK)				|
| type 			| varchar(255) not null	| transaction type [deposit, withdraw]	|
| date 			| timestamp not null	| transaction date						|
| amount 		| double not null		| transaction amount					|


### Dataset ###

#### Accounts table
| id  			| active	| balance   |
| -------------	| ---------	| --------- |
| 10011100099	| true		| 1100.53   |
| 20022200066 	| false		| 0         |
| 40044400011 	| true		| 500.00    |

#### banking_transactions table
| id	| account_id	| type		| date						| amount	|
| -----	| -------------	| ---------	| -------------------------	| ---------	|
| 1		| 10011100099	| deposit	| 2021-01-10 12:00:00.00	| 1100.00	|
| 2		| 10011100099	| deposit	| 2021-01-12 18:47:00.69	| 40.53		|
| 3		| 10011100099	| withdraw	| 2021-01-13 09:30:52.00	| 40.00	 	|
| 4		| 40044400011	| deposit	| 2021-01-01 13:00:00.00	| 500.00	|

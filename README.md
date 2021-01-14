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

### BDD tests with Cucumber ###

> Features are available in 
>> ./src/test/resources/com/oxiane/detienne/kata/features

> StepDefinitions are available in
>> ./src/test/java/com/oxiane/detienne/kata/bdd

> To run BDD tests in commandline : 
>>* mvn clean test

### E2E tests with Postman ###

> Launch Postman application

> Import Collection from file :
>> ./src/test/e2e/kata.postman_collection.json

> Run whole collection or just a specific test

### Unit tests with JUnit ###

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
| id  			| active	|
| -------------	| ---------	|
| 10011100099	| true		|
| 20022200066 	| false		|
| 40044400011 	| true		|

#### banking_transactions table
| id	| account_id	| type		| date						| amount	|
| -----	| -------------	| ---------	| -------------------------	| ---------	|
| 1		| 10011100099	| deposit	| 2021-01-10 12:00:00.00	| 1100.00	|
| 2		| 10011100099	| deposit	| 2021-01-12 18:47:00.69	| 40.53		|
| 3		| 10011100099	| withdraw	| 2021-01-13 09:30:52.00	| 40.00	 	|
| 4		| 40044400011	| deposit	| 2021-01-01 13:00:00.00	| 500.00	|

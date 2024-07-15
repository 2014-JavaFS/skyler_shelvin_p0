create table accounts(
accountId SERIAL PRIMARY KEY,
email VARCHAR(255) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL
);

create table expenses(
userId Integer not null,
itemId SERIAL PRIMARY key,
amount Integer,
category varchar(255),
date timestamp
);

----CRUD- create, read, update, delete (soft/hard)
-- sub-languages for accounts

insert into accounts(email, password)
values('testingFirst@mail.com', 'hoping4Success!');

insert into accounts(email, password)
values('secondTimeisAcharm@mail.com', 'successGranted'); 

--read account
select * from accounts
order by accountid asc;

--update account
update accounts 
set email = 'testingFirstAgain@mail.com'
where accountID = 1; 

--delete 
delete from accounts
where accountId = 1; 


--CRUD for expenses 

INSERT INTO expenses(userId, amount, category, date)
VALUES (1, 50.00, 'Groceries', CURRENT_TIMESTAMP);


INSERT INTO expenses(userId, amount, category, date)
VALUES (1, 250.00, 'Utilities', CURRENT_TIMESTAMP);


INSERT INTO expenses(userId, amount, category, date)
VALUES (2, 300.00, 'Restaurants', CURRENT_TIMESTAMP);

INSERT INTO expenses(userId, amount, category, date)
VALUES (2, 250.00, 'Utilities', CURRENT_TIMESTAMP);


select * from expenses;

select * from expenses 
where userId = 1; 

select * from expenses 
where userId = 1 
and category = 'Groceries'; 

update expenses 
set amount = 10
where userId = 1 
and itemId = 1; 

delete from expenses
where itemId = 1
and userId = 1; 








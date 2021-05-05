CREATE TABLE CUSTOMER (customerId int auto_increment primary key not null,
 name varchar(255),
  country varchar(255),
   dateOfBirth date,
   )
CREATE TABLE ORDERS(oderId int auto_increment primary key not null, customerId int, amount numeric(10, 2), vat numeric(3, 1), CONSTRAINT FK_ORDER_CUSTOMER FOREIGN KEY (customerId) REFERENCES CUSTOMER(customerId));
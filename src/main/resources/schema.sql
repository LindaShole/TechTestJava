CREATE TABLE IF NOT EXISTS CUSTOMER(
    CUSTOMERID INT PRIMARY KEY NOT NULL auto_increment,
    CUSTOMER_NAME VARCHAR(50),
    COUNTRY VARCHAR(50),
    DATE_OF_BIRTH DATE
    );

CREATE TABLE IF NOT EXISTS ORDERS (
    orderId int primary key not null auto_increment,
    amount number(10,2),
    vat number (3,1),
    customerid int,
    foreign key (CUSTOMERID) references CUSTOMER(CUSTOMERID)
);
-- --AUTO_INCREMENT
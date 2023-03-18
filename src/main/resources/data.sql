--INSERT INTO CUSTOMER (CUSTOMERID, CUSTOMER_NAME, COUNTRY, DATE_OF_BIRTH) VALUES (1,'Xolisani','South Africa', '2002-11-15');
INSERT INTO CUSTOMER (CUSTOMER_NAME, COUNTRY, DATE_OF_BIRTH) VALUES ('Xolisani','SA', '2002-11-15');
INSERT INTO CUSTOMER (CUSTOMER_NAME, COUNTRY, DATE_OF_BIRTH) VALUES ('Christopher','UK', '2003-12-31');
INSERT INTO CUSTOMER (CUSTOMER_NAME, COUNTRY, DATE_OF_BIRTH) VALUES ('Xerxes','UK', '2004-01-12');
INSERT INTO CUSTOMER (CUSTOMER_NAME, COUNTRY, DATE_OF_BIRTH) VALUES ('Judith','SA', '2005-05-01');
INSERT INTO CUSTOMER (CUSTOMER_NAME, COUNTRY, DATE_OF_BIRTH) VALUES ('Ashal','UK', '1999-02-14');
INSERT INTO CUSTOMER (CUSTOMER_NAME, COUNTRY, DATE_OF_BIRTH) VALUES ('Linda','SA', '2000-08-30');
INSERT INTO CUSTOMER (CUSTOMER_NAME, COUNTRY, DATE_OF_BIRTH) VALUES ('Jacob','MAU', '1991-05-01');
INSERT INTO CUSTOMER (CUSTOMER_NAME, COUNTRY, DATE_OF_BIRTH) VALUES ('Neal','UK', '1993-02-14');
INSERT INTO CUSTOMER (CUSTOMER_NAME, COUNTRY, DATE_OF_BIRTH) VALUES ('Umesh','SA', '1994-04-27');
INSERT INTO CUSTOMER (CUSTOMER_NAME, COUNTRY, DATE_OF_BIRTH) VALUES ('Glory','USA', '1994-04-27');
INSERT INTO CUSTOMER (CUSTOMER_NAME, COUNTRY, DATE_OF_BIRTH) VALUES ('Power','MAU', '1994-04-27');
INSERT INTO CUSTOMER (CUSTOMER_NAME, COUNTRY, DATE_OF_BIRTH) VALUES ('Thabiso','UK', '1995-05-14');
INSERT INTO CUSTOMER (CUSTOMER_NAME, COUNTRY, DATE_OF_BIRTH) VALUES ('Ockert','SA', '1996-06-7');
INSERT INTO CUSTOMER (CUSTOMER_NAME, COUNTRY, DATE_OF_BIRTH) VALUES ('Andile','USA', '1997-07-14');
INSERT INTO CUSTOMER (CUSTOMER_NAME, COUNTRY, DATE_OF_BIRTH) VALUES ('Heiko ','MAU', '1998-08-21');
commit ;

insert into orders(amount, vat, customerid) values (799.95, 1.15, 2);
insert into orders(amount, vat, customerid) values (69.75, 2.00, 3);
insert into orders(amount, vat, customerid) values (399.00, 1.5, 9);
insert into orders(amount, vat, customerid) values (279.95, 1.5, 13);
insert into orders(amount, vat, customerid) values (458.25, 2.25, 10);
insert into orders(amount, vat, customerid) values (1499.99, 2.00, 3);
insert into orders(amount, vat, customerid) values (799.95, 1.15, 7);
insert into orders(amount, vat, customerid) values (69.75, 2.00, 4);
insert into orders(amount, vat, customerid) values (399.00, 1.5, 1);
insert into orders(amount, vat, customerid) values (279.95, 1.5, 11);
insert into orders(amount, vat, customerid) values (458.25, 2.25, 1);
insert into orders(amount, vat, customerid) values (1499.99, 2.00, 9);
commit;

-- --AUTO_INCREMENT
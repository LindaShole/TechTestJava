/**
 * Author:  v-nchatitai
 * Created: 20 Sep 2022
 */

CREATE TABLE orders (
    order_id int PRIMARY KEY AUTO_INCREMENT,
    customer_id INT NOT NULL,
    amount NUMBER(10,2),
    vat NUMBER(3,1)
);


CREATE TABLE customer (
    customer_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_name varchar(100),
    country varchar(100),
    date_of_birth DATE
);
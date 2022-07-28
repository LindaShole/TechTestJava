create table if not exists customers (id bigint not null auto_increment, country varchar(255) not null, date_of_birth timestamp, name varchar(255) not null, quantity integer not null, total_amount numeric(19,2) not null, primary key (id));
create table if not exists orders (orderid bigint not null auto_increment, vat numeric(2,1) not null, amount numeric(10,2) not null, name varchar(255) not null, primary key (orderid));
create table if not exists customer_orders (customer_id bigint not null auto_increment, order_id bigint not null, primary key (customer_id, order_id));
create sequence hibernate_sequence start with 1 increment by 1;
alter table customer_orders add constraint FKnswk7m4x392evesx9s4vkks4w foreign key (order_id) references orders;
alter table customer_orders add constraint FKiwewyud8svmsotwucvm94aaw8 foreign key (customer_id) references customers;

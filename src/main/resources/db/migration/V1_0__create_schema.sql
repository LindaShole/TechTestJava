CREATE TABLE customers
(
    id bigserial NOT NULL,
    country character varying(10),
    date_of_birth date,
    name character varying(50),
    CONSTRAINT customers_pkey PRIMARY KEY (id)
);


CREATE TABLE orders
(
    id bigserial NOT NULL,
    amount numeric(19,2),
    customer_id bigint NOT NULL,
    vat numeric(19,2),
    CONSTRAINT orders_pkey PRIMARY KEY (id),
    CONSTRAINT fk_order_customer FOREIGN KEY (customer_id) REFERENCES customers (id)
);

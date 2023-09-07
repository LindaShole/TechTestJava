create table "ORDER"
(
    ID           UUID     not null,
    AMOUNT       DOUBLE   not null,
    VAT          DOUBLE   not null,
    CUSTOMER_ID  UUID     not null,
    CREATED      datetime not null,
    LAST_UPDATED datetime not null,
    constraint "ORDER_pk"
        primary key (ID),
    constraint "ORDER_CUSTOMER_ID_fk"
        foreign key (CUSTOMER_ID) references CUSTOMER
);

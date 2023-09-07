create table CUSTOMER
(
    ID            UUID                   not null,
    NAME          CHARACTER VARYING(255) not null,
    DATE_OF_BIRTH DATE                   not null,
    COUNTRY       CHARACTER VARYING(255) not null,
    CREATED       TIMESTAMP              not null,
    LAST_UPDATED  TIMESTAMP              not null,
    constraint CUSTOMER_PK
        primary key (ID)
);

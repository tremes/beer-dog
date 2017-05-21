
CREATE SCHEMA IF NOT EXISTS beerdog;

DROP TABLE IF EXISTS address;
DROP TABLE IF EXISTS beer;
DROP TABLE IF EXISTS restaurant;

create table ADDRESS (
    ADDRESS_ID int8 not null,
    OBJ_VERSION int4,
    STREET varchar(255) not null,
    ZIPCODE varchar(16) not null,
    CITY varchar(255) not null,
    primary key (ADDRESS_ID)
);

create table BEER (
    BEER_ID int8 not null,
    OBJ_VERSION int4,
    NAME varchar(255) not null,
    RATING decimal,
    TYPE varchar(255),
    VOL decimal,
    DEGREES int8,
    BREW varchar(255),
    primary key(BEER_ID)
);


create database if not exists IotDB;

use IotDB;

create table if not exists temperature(
    id int auto_increment not null ,
    event_date date,
    event_time time,
    target varchar(20),
    property varchar(20),
    data int,
    primary key (id)
);

create table if not exists humidity(
  id int auto_increment not null ,
  event_date date,
  event_time time,
  target varchar(20),
  property varchar(20),
  data int,
  primary key (id)
);

create table if not exists noise(
  id int auto_increment not null ,
  event_date date,
  event_time time,
  target varchar(20),
  property varchar(20),
  data int,
  primary key (id)
);

create table if not exists light(
  id int auto_increment not null ,
  event_date date,
  event_time time,
  target varchar(20),
  property varchar(20),
  data int,
  primary key (id)
);

create table if not exists air_quality(
  id int auto_increment not null ,
  event_date date,
  event_time time,
  target varchar(20),
  property varchar(20),
  co int,
  pm25 int,
  pm10 int,
  o2 int,
  co2 int,
  level varchar(3),
  primary key (id)
);
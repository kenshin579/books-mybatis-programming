create schema journal collate utf8_general_ci;

create table account
(
  ACCOUNT_NAME varchar(255) not null,
  PASSWORD varchar(255) not null,
  ID bigint unsigned auto_increment,
  ENABLED tinyint(1) default '1' null,
  constraint ID
    unique (ID)
);

create table entry
(
  ID bigint auto_increment
    primary key,
  CREATED datetime null,
  SUMMARY varchar(255) null,
  TITLE varchar(255) null
);


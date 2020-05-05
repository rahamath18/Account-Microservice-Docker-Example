CREATE SCHEMA account;

create table ACCOUNT (
	ID integer not null auto_increment, 
	USER_NAME varchar(20) not null, 
	PASSWORD varchar(12) not null, 	
	TOKEN varchar(12), 
	STATUS varchar(12) not null, 
	VERSION integer not null, 
	LOGICALLY_DELETED varchar(1) not null, 	
	CREATED_BY varchar(20) not null, 
	CREATED_DATE datetime not null, 
	UPDATED_BY varchar(20), 
	UPDATED_DATE datetime, 
	primary key (ID)
);

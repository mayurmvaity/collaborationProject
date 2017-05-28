create table usertable (
	userid number(6) primary key,
	fname varchar2(20) not null,
	lname varchar2(20) not null,
	email varchar2(50) not null,
	pno varchar2(10) not null,
	pw varchar2(20) not null,
	add1 varchar2(50) not null,
	add2 varchar2(50) not null,
	add3 varchar2(50) not null,
	city varchar2(50) not null,
	state varchar2(50) not null,
	pincode varchar2(50) not null,
	role varchar2(15) not null,
	is_active char(1) not null,
	is_online char(1) not null,
	is_approved char(1) not null
); 

create sequence foruserid
start with 1
increment by 1
nocache
nocycle;

/****** BLOG *******/

create sequence forblogid
start with 1
increment by 1
nocache
nocycle;


create table blog (
	blogid number(6) primary key,
	btitle varchar2(25) not null,
	bdata clob not null,
	blikes number(6),
	userid number(10),
	bcomments number(6),
	bdate date,
	is_active char(1) not null,
	is_approved char(1) not null
);

/********* FORUM *********/
create sequence forforumid
start with 1
increment by 1
nocache
nocycle;

create table forum (
	forumid number(6) primary key,
	ftitle varchar2(25) not null,
	fdata varchar2(500) not null,
	fdate date,
	userid number(10),
	is_active char(1) not null
);

/********** JOB ***********/
create sequence forjobid
start with 1
increment by 1
nocache
nocycle;

create table job (
	jobid number(6) primary key,
	jtitle varchar2(120) not null,
	jdata clob not null,
	jdate date,
	userid number(10),
	is_active char(1) not null
);

/************ Events ***********/
create sequence foreventid
start with 1
increment by 1
nocache
nocycle;

create table adminevent (
	evtid number(6) primary key,
	etitle varchar2(50) not null,
	edata clob not null,
	edate date,
	userid number(10),
	is_active char(1) not null
);

/************** blog comment ************/
create sequence forblogcommid
start with 1
increment by 1
nocache
nocycle;

create table blogcomment (
	blogcommid number(6) primary key,
	commdata clob not null,
  	blogid number(6),
	commdate date,
	userid number(10),
	is_active char(1) not null
);

/************* forum member ************/
create sequence forforummemberid
start with 1
increment by 1
nocache
nocycle;

create table forummember (
	forummemberid number(6) primary key,
	userid number(10),
	forumid number(10),
	is_active char(1) not null,
	is_approved char(1) not null
);
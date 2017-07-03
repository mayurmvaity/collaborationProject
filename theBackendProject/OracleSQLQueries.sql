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
	profile varchar2(500),
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

alter table usertable add gender varchar2(10);
alter table usertable add status varchar2(10);


/****** BLOG *******/

create sequence forblogid
start with 1
increment by 1
nocache
nocycle;


create table blog (
	blogid number(6) primary key,
	btitle varchar2(500) not null,
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
	ftitle varchar2(120) not null,
	fdata clob not null,
	fdate date,
	userid number(10),
	is_active char(1) not null
);

ALTER TABLE forum
ALTER COLUMN ftitle varchar2(500); 

/********** JOB ***********/
create sequence forjobid
start with 1
increment by 1
nocache
nocycle;

create table job (
	jobid number(6) primary key,
	jtitle varchar2(500) not null,
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
	etitle varchar2(500) not null,
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
create sequence forfmemberid
start with 1
increment by 1
nocache
nocycle;

create table fmember (
	fmemberid number(6) primary key,
	userid number(10),
	forumid number(10),
	is_active char(1) not null,
	is_approved char(1) not null
);

/******** forum posts ********/
create sequence forfpostid
start with 1
increment by 1
nocache
nocycle;

create table fpost (
	fpostid number(6) primary key,
	forumid number(10),
	userid number(10),
	fpdata clob not null,
	fpdate date,
	is_active char(1) not null
);

/*********** friend ***************/
create sequence forfriendid
start with 1
increment by 1
nocache
nocycle;

create table friend (
	friendid number(6) primary key,
	userid1 number(10) not null,
	userid2 number(10) not null,
	is_friend char(1) not null,
	is_active char(1) not null

);
/************* job application (jobapp) ************/
create sequence forjobappid
start with 1
increment by 1
nocache
nocycle;

create table jobapp (
	jobappid number(6) primary key,
	jobid number(6) not null,
	userid number(6) not null,
	appdate date,
	is_active char(1) not null
);

/*************** Event participants list ***************/
create sequence foreventpartid
start with 1
increment by 1
nocache
nocycle;

create table eventpart (

	eventpartid number(6) primary key,
	evtid number(6) not null,
	userid number(6) not null,
	epdate date,
	is_active char(1) not null
);
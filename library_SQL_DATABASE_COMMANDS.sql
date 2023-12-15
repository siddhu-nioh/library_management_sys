create database library;
use library;
create table students(id int auto_increment primary key,regno varchar(20) unique,password varchar(20));
create table books(bookId int,bookName varchar(20),bookAuthor varchar(20),bookGenre varchar(20));
insert into students values(1,"20A21A0427","root");
insert into books values(1,"Ramayanam","valmiki","mythology");
select * from students;
drop table books;
create table books(bookId int,bookName varchar(20),bookAuthor varchar(20),bookGenre varchar(20),Publications varchar(20));
insert into books values(1,"Ramayanam","valmiki","mythology","nethaji");
select * from books;
update books  set dueDate= DATE_ADD(CURDATE(),INTERVAL 1 WEEK) where available = true;
drop table  books;
set sql_safe_updates=0;
ALTER TABLE books
ADD COLUMN dueDate DATE,
ADD COLUMN reminderSent BOOLEAN DEFAULT FALSE;
update books set dueDate="2023-08-02" where bookId=1;
ALTER TABLE books
ADD COLUMN latefee double;
SELECT CURDATE();
update books  set dueDate=curdate()  ;
 SELECT CURDATE() 'start date',
 DATE_ADD(CURDATE(),INTERVAL 1 WEEK) 'one week later';

insert into books values(2,"Ramam","vaki","mythology","nethaji","2023-12-12",false,5.0);
insert into books values(3,"valam","saki","mythology","nethaji","2023-12-12",default,null);
update books set bookId=3 where latefee=null;
update books set latefee=5.0 where available=true;
select * from books;
delete from books where bookId=2;
ALTER TABLE books
ADD COLUMN available boolean default true;
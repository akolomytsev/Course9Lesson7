create table if not exists students (id bigserial primary key, name varchar(255), age int);

insert into students (name, age)
values
('Bob', 100),
('Jack', 80),
('John', 90);
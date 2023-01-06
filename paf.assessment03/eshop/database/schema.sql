rop database if exists eshop;

create database eshop;

use eshop;

create table customers (

    customer_name varchar(32) not null,
    ship_address varchar(128) not null, 
    email_address varchar(128) not null, 

    primary key(customer_name)
);

--task 3(c)--
create table order (

    order_id int auto_increment not null,
    delivery_id varchar(32) not null, 
    customer_name varchar(128) unique, 
    ship_address varchar(128) not null, 
    email_address varchar(128) not null,
    order_status varchar(32) not null,
    order_date date not null,

    primary key(order_id)
    constraint fk_user_id
    foreign key(customer_name) references customers(customer_name)
);

create table order_status(
    order_id int auto_increment not null,
    delivery_id varchar(32) not null,
    order_status varchar(32) not null,
    status_update varchar(32) not null,

    primary key(order_id)   
    constraint fk_user_id
    foreign key(customer_name) references customers(customer_name) 
);

insert into 
	customers(customer_name, ship_address, email_address)
    values('fred', '201 Cobblestone Lane', 'fredflintstone@bedrock.com');

select *from customers where customer_name = 'fred';
create database pubg_spring;
create user 'sdmunhoz'@'%' identified by "root";
grant all privileges on pubg_spring.* to 'sdmunhoz'@'%';

set @@global.time_zone = '+00:00';
set @@session.time_zone = '+00:00';
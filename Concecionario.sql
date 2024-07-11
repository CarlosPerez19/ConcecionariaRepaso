create database concecionario;
use concecionario;

create table vehiculos (
	placa varchar(8) not null primary key,
    marca varchar (25) not null,
    cilindraje varchar (25),
    combustible varchar (25) not null,
    color varchar (25) not null,
    propietario varchar (25) not null
);

select * from vehiculos;
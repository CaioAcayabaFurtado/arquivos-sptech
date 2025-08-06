-- GRUPO 3 - P.I.:
-- Caio Acayaba Furtado, RA: 03241026 
-- Eduardo Alves Azevedo, RA: 03241025 
-- Eduardo Facini Dorval, RA: 03241042 
-- Kauã Medrado Navarro, RA:03241069 
-- Luiz Gustavo Dantas, RA: 03241060 
-- Marcello Manfré, RA: 03241062 
-- Pedro Vinicius da Silva dos Santos, RA; 03241054 

create database grupo3;


-- 	TABELA para marcar a quantidade de acionamento do suposto alarme --

use grupo3;

create table acionamento (
id_acionamento int primary key auto_increment,
qnt_acionamento float,
mes date
) auto_increment=100;


-- Tabela para temp maxima -- 

use grupo3;

create table max_temp (
id_max int primary key auto_increment, 
max_temp float,
Dia date
) auto_increment=1;

-- TABELA para temp minima  -- 

use grupo3;

create table minm_temp (
id_min int primary key auto_increment,
minm_temp float,
dia date
) auto_increment=200;

-- Tabela temperatura atual --

create table atual_temp (
id_atual int primary key auto_increment,
minm_temp float,
dia date
) auto_increment=300;

-- Tabela cadastro de cada arduino --

create table sistema (
id_arduino int primary key auto_increment,
nome varchar (50),
contato varchar (50),
categoria varchar (50),
estado varchar (50),
cidade varchar (50),
logradouro varchar (50)
) auto_increment=300;



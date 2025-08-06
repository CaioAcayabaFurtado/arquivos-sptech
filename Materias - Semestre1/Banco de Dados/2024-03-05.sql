create database aula4;
use aula4;

create table funcionario (
id int primary key auto_increment,
nome varchar (60) not null,
salario decimal (6,2) default 1.99,
dtatual datetime default current_timestamp
) auto_increment = 100;

alter table funcionario add column cpf char (11) unique;

-- npt null: campo nao pode ser nulo
-- default: padrão, se nulo inserir padrao
-- unique: um acmpo que nao pode se repetir

describe funcionario;

insert into funcionario values
	(default, 'Jesse', 100.00, default, '0123467899');
    
select * from funcionario;

insert into funcionario (nome) values
	('walter');
    
select * from funcionario;

-- formatando uma data
select date_format(dtatual, '%d/%m/%y %h:%m') from funcionario;

-- juntar duas categoria em uma coluna
select concat(nome,salario) from funcionario;
select concat(nome,' ',salario) from funcionario;
select concat('O salario do ', nome,' é R$',salario) from funcionario;

-- alias
select salario from funcionario;
select salario  as Salário from funcionario;
select nome as 'Nome do funcionário', salario as Salário from funcionario;
select nome as 'Nome do funcionário', concat('R$', salario) as Salário from funcionario;

-- not null
-- unique
-- default
-- alias = as
-- concat()

-- numeros decimais:

-- float - 7digitos
-- double - 15 digitos
-- decimal() - 32 digitos
	-- decimal(6,3) = 123,456
    -- decimal(7,2) = 12345,67
    
    insert into funcionario (nome) values
		('douglas');
        
	select * from funcionario;
    truncate table funcionario;
    desc funcionario;
    
    
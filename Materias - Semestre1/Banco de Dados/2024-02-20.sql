use sprint1;

-- TABELA PESSOA --

create table pessoa (
id int primary key,
nome varchar (50),
altura float,
dtNasc date
);
-- numero decimal: float= 7 caracteres, double= 15 caracteres, decimal 32 caracteres
    -- decimal (8,4) 1234,5678

desc pessoa;

insert into pessoa values
(100, 'marcello', 1.50, '2003-04-01');

select * from pessoa;
alter table pessoa modify column id int auto_increment;
select * from pessoa;

insert into pessoa values
(default, 'carolina', 1.70, '2006-08-12');



-- inserir coisas especificas --
insert into pessoa (nome,altura) values
('giulia', 1.40);
select * from pessoa;

-- atualizar nome
update pessoa set nome= 'GIULIA' where id=102;

-- adicionar informacao
update pessoa set dtNasc= '2005-08-16' where id= 102;

-- excluir nome
delete from pessoa where id=102;

-- adicionar coluna
alter table pessoa add column salario decimal (10,2);
update pessoa set salario= 1.99 where id=100;
-- ou
update pessoa set salario= 5.80 where id= 100 or id=101;

-- excluir coluna 
alter table pessoa drop column dtNasc;

-- LIMPAR OS DADOS DA TABELA
truncate table pessoa;

insert into pessoa (nome) values
('Marcello');

-- adicionar campo chamado genero
alter table pessoa add column genero char (1);
-- constrain (configuração/restrição)
alter table pessoa add constraint chkGenero check (genero in ('F', 'M'));

insert into pessoa (nome, genero) values
('carolina', 'F');

alter table pessoa add column email varchar (50),  add constraint chkEmail check (email like '%@%');

drop table pessoa;


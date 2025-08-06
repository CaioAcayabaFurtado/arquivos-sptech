use sisa;

create table empresa (
idEmpresa int primary key auto_increment,
nome varchar (45),
responsavel varchar (45)
);

insert into empresa values
	(default, 'Stefanini', 'Daniela'),
    (default, 'C6BANK', 'Vanessa');
    
select * from empresa;

create table aluno (
ra char (8) primary key,
nome varchar (45),
bairro varchar (45),
fkEmpresa int not null,
constraint fkAlunoEmpresa foreign key (fkEmpresa) references empresa (idEmpresa)
);

insert into aluno values
	('03241999', 'Cebolinha', 'Consolação', 1),
    ('03241998', 'Monica', 'Paraíso', 1),
    ('03241997', 'Cascão', 'Trianon Masp', 2);
    
select * from aluno;

-- ALTER TABLE: Caso não queira colocar no create table.
-- alter table aluno add constraint fkAlunoEmpresa foreign key (fkEmpresa) references empresa (idEmpresa); 

-- Primeiros join
select * from aluno join empresa on idEmpresa = fkEmpresa;
select aluno.nome, empresa.nome from aluno join empresa on idEmpresa = fkEmpresa;
select aluno.nome as NomeAluno, empresa.nome as NomeEmpresa from aluno join empresa on idEmpresa = fkEmpresa;
select a.nome as 'Nome do Aluno', e.nome as 'Nome da Empresa' from aluno as a join empresa as e on idEmpresa = fkEmpresa;
select a.nome as 'Nome do Aluno', e.nome as 'Nome da Empresa' from aluno as a join empresa as e on idEmpresa = fkEmpresa where e.nome = 'Stefanini';








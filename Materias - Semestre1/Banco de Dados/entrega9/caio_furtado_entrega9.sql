create database pratica9;
use pratica9;

-- TABELA ALUNO --
create table aluno(
ra int primary key,
nome varchar(45),
email varchar (45),

fk_grupo int,
	constraint FKGrupoAluno foreign key (fk_grupo) references grupo (id_grupo)
);
	insert into aluno values
		(03242010, 'Ana', '@sptech.school', 1),
		(03242020, 'Joao', '@sptech.school', 1),
		(03242030, 'Maria', '@sptech.school', 2),
		(03242040, 'Gabriel', '@sptech.school', 2);
        
-- -------------------------------------------------------------------------------------------------------------------------------------------------

-- TABELA GRUPO --
create table grupo(
id_grupo int primary key auto_increment,
nome varchar(45),
descricao varchar (90)
);
	insert into grupo values
		(default, 'SafeSoy', 'Monitoramento de safras de soja'),
		(default, 'RotaVerde', 'Controle da emissão de CO2 no turismo brasileiro');

-- -------------------------------------------------------------------------------------------------------------------------------------------------

-- TABELA PROFESSOR -- 
create table professor(
id_professor int primary key auto_increment,
nome varchar (45),
disciplina varchar (45)
) auto_increment = 10000;
	insert into professor values
		(default, 'Frisa', 'Algoritimos'),
		(default, 'Brandão', 'Pesquisa e inovação'),
		(default, 'Vivian', 'Banco de dados');        
        
-- -------------------------------------------------------------------------------------------------------------------------------------------------

-- TABELA SISTEMA --
create table sistema(
fk_grupo int,
	constraint FkGrupoSistema foreign key (fk_grupo) references grupo (id_grupo),
fk_professor int,
	constraint FkProfessorSistema foreign key (fk_professor) references professor (id_professor),
    
constraint primary key (fk_grupo, fk_professor),

nota int,
datahora datetime
);
	insert into sistema values
		(1, 10000, 10,'2024-04-29 9:30'),
		(1, 10001, 8,'2024-04-29 9:30'),
        (2, 10000, 8, '2024-06-12 10:00'),
        (2, 10002, 9, '2024-06-12 10:00');
        
-- -------------------------------------------------------------------------------------------------------------------------------------------------

-- 	SELECTS:

select * from aluno;
select * from grupo;
select * from professor;
select * from sistema;

select grupo.*, aluno.* from aluno
	join grupo
		on id_grupo = fk_grupo;
        
select avg(nota) from sistema;
select max(nota), min(nota) from sistema;
select sum(nota) from sistema;

select professor.*, grupo.*, nota, datahora from professor
	join sistema
		on fk_professor = id_professor
    join grupo
		on id_grupo = fk_grupo;
        
select professor.*, grupo.*, datahora from professor
	join sistema
		on fk_professor = id_professor
    join grupo
		on id_grupo = fk_grupo
	where id_grupo = 1;
    
select professor.nome, grupo.nome from grupo
	join sistema
		on id_grupo = fk_grupo
	join professor
		on id_professor = fk_professor
	where id_professor = 10000;
    

select grupo.*, aluno.*, professor.*, nota, datahora from professor
	join sistema
		on fk_professor = id_professor
    join grupo
		on id_grupo = sistema.fk_grupo
	join aluno
		on id_grupo = aluno.fk_grupo;
        
select distinct (nota) from sistema;

select nome, avg(nota), sum(nota) from professor
	join sistema
		on fk_professor = id_professor
    group by nome;

select nome, avg(nota), sum(nota) from grupo
	join sistema
		on fk_grupo = id_grupo
	group by nome;
    
select nome, max(nota), min(nota) from professor
	join sistema
		on fk_professor = id_professor
    group by nome;

select nome, max(nota), min(nota) from grupo
	join sistema
		on fk_grupo = id_grupo
	group by nome;

    

        

        





        
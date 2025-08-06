create database entregas;
use entregas;

-- TABELA PROJETO --
create table projeto (
id_projeto int primary key auto_increment,
nomeProjeto varchar (45),
descricao varchar (90)
);

	insert into projeto (nomeProjeto, descricao) values
		('SafeSoy', 'monitoramento soja'),
        ('Projeto I.T.','monitoramento de medicamento'),
        ('Termal food','monitoramento de alimentos');
        
-- ------------------------------------------------------------------------------------------------------------

-- TABELA ALUNO --

create table aluno (
ra int primary key,
nomeAluno varchar (45),
telefone varchar (45),

fk_representante int,
	constraint FkRepresentanteAluno foreign key (fk_representante) references aluno (ra),

fk_projeto int,
	constraint FkProjetoAluno foreign key (fk_projeto) references projeto (id_projeto)
);

	insert into aluno values
		('03241011','Luana','11 934824729', null,'1'),
        ('03241012','Fabricio','11 938475934','03241011','2'),
        ('03241013','Matheus','11 934759345','03241011','3');
        
-- ------------------------------------------------------------------------------------------------------------
 
 -- TABELA ACOMPANHANTE -- 
 
 create table acompanhante (
 id_acompanhante int primary key auto_increment,
 nomeAcompanhante varchar (45),
 relacao varchar (45),
	
fk_aluno int,
	constraint FkAlunoAcomp foreign key (fk_aluno) references aluno (ra)
) auto_increment = 100;

	insert into acompanhante (nomeAcompanhante, relacao, fk_aluno) values
		('Lourdes','avó','03241011'),
        ('Renata','mãe','03241013'),
        ('Lucas','irmão','03241013');
        
-- ------------------------------------------------------------------------------------------------------------
drop table acompanhante;
drop table aluno;
drop table projeto;

	-- SELECTS:
      
select * from aluno;
select * from projeto;
select * from acompanhante;

select ra, nomeAluno, telefone, nomeProjeto, descricao from aluno
	join projeto
		on fk_projeto = id_projeto;
        
select ra, nomeAluno, telefone, nomeAcompanhante, relacao from aluno
	join acompanhante
		on fk_aluno = ra;
	
select Aluno.ra, Aluno.nomeAluno, Aluno.telefone, Representante.nomeAluno from aluno as Aluno
	join aluno as Representante
		on Aluno.fk_representante = Representante.ra;
        
select ra, nomeAluno, telefone, nomeProjeto, descricao from aluno
	join projeto
		on fk_projeto = id_projeto
			where nomeProjeto = 'SafeSoy';
            
select Aluno.ra, Aluno.nomeAluno, Aluno.telefone, nomeProjeto, Representante.nomeAluno, nomeAcompanhante from aluno as Aluno
	join aluno as Representante
		on Aluno.fk_representante = Representante.ra
	join projeto 
		on Aluno.fk_projeto = id_projeto
	join acompanhante
		on fk_aluno = Aluno.ra;

-- ------------------------------------------------------------------------------------------------------------
-- ------------------------------------------------------------------------------------------------------------
-- ------------------------------------------------------------------------------------------------------------
-- ------------------------------------------------------------------------------------------------------------
-- ------------------------------------------------------------------------------------------------------------
-- ------------------------------------------------------------------------------------------------------------
-- ------------------------------------------------------------------------------------------------------------
-- ------------------------------------------------------------------------------------------------------------
-- ------------------------------------------------------------------------------------------------------------
-- ------------------------------------------------------------------------------------------------------------

-- TABELA ORGANIZADOR --
create table organizador (
id_organizador int primary key auto_increment,
nomeOrganizador varchar (45),
email varchar (45),
	constraint Ckarb check (email like '%@%'),
rua varchar (45),
bairro varchar (45),

fk_experiente int,
	constraint FkExperienteOrg foreign key (fk_experiente) references organizador (id_organizador)
) auto_increment = 30;

	insert into organizador (nomeOrganizador, email, rua, bairro, fk_experiente) values
		('Marcio','marcio@gmail.com','rua fulano de tal','paraiso',null),
        ('Rebeca','rebeca@gmail.com','rua tralala','morumbi','30'),
        ('João','joao@gmail.com','rua numseioq','bela vista','30');
        
-- ------------------------------------------------------------------------------------------------------------

-- TABELA CAMPANHA --
create table campanha (
id_campanha int primary key auto_increment,
nomeCampanha varchar (45),
categoria varchar (45),
instituicao1 varchar (45),
instituicao2 varchar (45),
dataFinal date,

fk_organizador int,
	constraint FkOrganizadoCampanha foreign key (fk_organizador) references organizador (id_organizador)
) auto_increment = 500;

	insert into campanha (nomeCampanha, categoria, instituicao1, instituicao2, dataFinal, fk_organizador) values
		('Life autism','voluntariamento','Apae','Prefeitura municipal','2024-11-03', 30),
        ('Health Diabetes','medicamento','Drograsil','Insuliner','2024-12-03', 30),
        ('Contra Dengue','conscientizacao','Prefeitura municipal',null,'2024-06-25', 31),
        ('Vascine-se já','conscientizacao','Prefeitura municipal','SUS','2025-01-15', 32);
        
-- ------------------------------------------------------------------------------------------------------------
drop table organizador;
drop table campanha;

	-- SELECTS:
    
select * from organizador;
select * from campanha;

select * from organizador
	join campanha
		on fk_organizador = id_organizador;
        
select * from organizador
	join campanha
		on fk_organizador = id_organizador
			where nomeOrganizador = 'Marcio';
	
select Org.nomeOrganizador, Org.email, Org.rua, Org.bairro, Exp.nomeOrganizador as Experiente from organizador as Org
	join organizador as Exp
		on Org.fk_experiente = Exp.id_organizador;
        
select Org.nomeOrganizador, Org.email, Org.rua, Org.bairro, Exp.nomeOrganizador as Experiente from organizador as Org
	join organizador as Exp
		on Org.fk_experiente = Exp.id_organizador
			where Exp.nomeOrganizador = 'Marcio';
            
select Org.nomeOrganizador, Org.email, Org.rua, Org.bairro, Exp.nomeOrganizador as Experiente, nomeCampanha from organizador as Org
	join organizador as Exp
		on Org.fk_experiente = Exp.id_organizador
	join campanha
		on fk_organizador = Org.id_organizador
			where Org.nomeOrganizador = 'João';
			

            




        
        
        
        
        
        
        
        
        
        
        
        
        
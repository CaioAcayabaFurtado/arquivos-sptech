create database entrega4;
use entrega4;

create table aluno (
ra char (8) primary key,
nome varchar (45),
telefone varchar (11),

fk_representante char (8),
	constraint fkRepresentante foreign key (fk_representante) references aluno (ra),

fk_projeto int,
	constraint fkProjeto foreign key (fk_projeto) references projeto(idprojeto)
);
drop table aluno;
insert into aluno (ra, nome, telefone) values
	(12345678, 'João', '119999999'),
	(23456789, 'Maria', '118888888');
update aluno set fk_representante = 23456789 where ra = (12345678);
update aluno set fk_projeto = 1 where ra in (12345678, 234567890);

insert into aluno (ra, nome, telefone, fk_representante, fk_projeto)values
 	('12345678', 'João', '119999999', '23456789', 1),
 	('23456789', 'Maria', '118888888', null, 1);
-- SQL_SAFE_UPDATES = 0;

    

-- ---------------------------------------------------------------------------------------------------------------------------------------------------

create table projeto (
idprojeto int primary key auto_increment,
nomeprojeto varchar (45),
descprojeto varchar (90)
);

insert into projeto values
	(default, 'Sprint2', 'Desenvolvimento de um site estático.');
    
-- ---------------------------------------------------------------------------------------------------------------------------------------------------
    
select ra as 'RA', nome as 'Nome', telefone as 'Telefone', nomeprojeto as 'Projeto' from aluno 
join projeto 
on idprojeto = fk_projeto;
    
select Representado.ra as 'RA', Representado.nome as 'Nome', Representado.telefone as 'Telefone', 
	   representante.nome as 'Representante' 
	from aluno as Representado join aluno as Representante
	on Representado.fk_representante = representante.ra;
    
select * from aluno join projeto
	on idprojeto = fk_projeto
		where idprojeto = 1;
    
-- ---------------------------------------------------------------------------------------------------------------------------------------------------
-- ---------------------------------------------------------------------------------------------------------------------------------------------------
-- ---------------------------------------------------------------------------------------------------------------------------------------------------
-- ---------------------------------------------------------------------------------------------------------------------------------------------------
-- ---------------------------------------------------------------------------------------------------------------------------------------------------
-- ---------------------------------------------------------------------------------------------------------------------------------------------------
-- ---------------------------------------------------------------------------------------------------------------------------------------------------
-- ---------------------------------------------------------------------------------------------------------------------------------------------------
-- ---------------------------------------------------------------------------------------------------------------------------------------------------

create table organizador (
idorganizador int primary key auto_increment,
nome varchar (45), 
endereco varchar (45),
email varchar (256),

fk_experiente int,
	constraint fkExperiente foreign key (fk_experiente) references organizador(idorganizador) 
) auto_increment = 30;
drop table organizador;

insert into organizador (idorganizador, nome, endereco, email) values
	(default, 'Fábio', 'Rua Xiquexique Bahia, Centro','fabinhobahia@gmailcom'),
    (default, 'Joana', 'Rua Carlos Brino, Jardim Felicidade', 'joana1977@gmail.com'),
    (default, 'Claudio', 'Rua Ronaldo Fenomeno, Liberdade', 'claudiodorojao@hotmail.com');
    
update organizador set fk_experiente = 32 where idorganizador in (30, 31);

-- ---------------------------------------------------------------------------------------------------------------------------------------------------
    
create table campanha (
idcampanha int primary key auto_increment,
nome varchar (45),
categoria varchar (45),
instituicao1 varchar (45),
instituicao2 varchar (45),
datafinal date,

fk_organizador int,
	constraint FkOrganizador foreign key (fk_organizador) references organizador(idorganizador)
) auto_increment = 500;
drop table campanha;

insert into campanha (idcampanha, nome, categoria, instituicao1, instituicao2, datafinal) values 
	(default, 'Teleton', 'alimento', 'Safra', 'GRAAX', '2015-11-03'),
    (default, 'Casa da criança', 'Produtos de higiene', 'Casa da criança', 'UNICEF', '2024-08-19'),
    (default, '', 'Campanha Agasalho', 'Drograsil', 'UNIMED', '2024-06-05');
    
update campanha set fk_organizador = 31 where idcampanha in (500, 502);
update campanha set fk_organizador = 30 where idcampanha = 501;

-- ---------------------------------------------------------------------------------------------------------------------------------------------------

select * from organizador 
		 join campanha
		 on idorganizador = fk_organizador;
    
select * from campanha 
		join organizador
		on idorganizador = fk_organizador where idorganizador = 30;
    
select * from organizador as Novato 
		 join organizador as Veterano
	     on Veterano.idorganizador = Novato.fk_experiente;
    
select * from organizador as Novato
		 join campanha
		 on idorganizador = fk_organizador
         join organizador as Veterano
	     on Veterano.idorganizador = Novato.fk_experiente;

-- por algum motivo ficou invertido os dados...
    








    


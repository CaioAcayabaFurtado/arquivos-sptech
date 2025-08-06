-- PRATICA 1 pt2 --

-- TABELA 1 --

use sprint1;
create table atleta (
idatleta int primary key,
nome varchar (40),
modalidade varchar (40),
qntmedalha int (3)
);

desc atleta;

insert into atleta values
('123', 'Pedro', 'volei', 10),
('124', 'Marcelo', 'futebol', 6),
('125', 'Gabriel', 'volei', 8),
('126', 'Mateus', 'handball', 5),
('127', 'Joana', 'futebol', 3);

select * from atleta;

update atleta set qntmedalha= 5 where idatleta= '125';
select * from atleta;

update atleta set qntmedalha= 7 where idatleta= '125' or idatleta= '126';
select * from atleta;	

update atleta set nome= 'Marcello' where idatleta= '123';
select * from atleta;	

alter table atleta add column dtNasc date;
update atleta set dtNasc= '2004-08-24' where idatleta= '123' or idatleta= '124' or idatleta= '125' or idatleta= '126' or idatleta= '127';
select * from atleta;
-- sim, são todos irmãos gêmeos! (uma familia de atletas) :)

delete from atleta where idatleta= '124';
select * from atleta;

select * from atleta where modalidade!= 'volei';
select * from atleta where qntmedalha>= 7;

alter table atleta modify modalidade varchar (60);
desc atleta;

truncate table atleta;
select * from atleta;
drop table atleta;



-- TABELA 2 --
use sprint1;

create table musica (
idmusica int primary key,
titulo varchar (40),
artista varchar (40),
genero varchar (40)
);

desc musica;
insert into musica values
(1, 'Samba', 'Lagum', 'Samba'),
(2, 'Rosa Norte', 'Armandinho', 'Reggae'),
(3, 'Campeão', 'Dudu', 'Trap'),
(4, 'Proibida Pra Mim', 'CharlieBrownJr', 'Rock'),
(5, 'Doce Veneno', 'CostaGold', 'Rap'),
(6, 'Dia Azul', 'Teto', 'Trap'),
(7, 'Meu Lugar', 'Arlindo Cruz', 'Samba');

select * from musica;

alter table musica add column curtidas int;
update musica set curtidas= 500 where idmusica= 1;
update musica set curtidas= 34598 where idmusica= 2;
update musica set curtidas= 12371 where idmusica= 3;
update musica set curtidas= 43509 where idmusica= 4;
update musica set curtidas= 5908 where idmusica= 5;
update musica set curtidas= 82734 where idmusica= 6;
update musica set curtidas= 9874 where idmusica= 7;
select * from musica;

alter table musica modify artista varchar (80);
desc musica;

update musica set curtidas= 2934 where idmusica= '2';
update musica set curtidas= 8989 where idmusica in ('1', '6');
select * from musica;

delete from musica where idmusica= '2';

select * from musica where genero!= 'Samba'; 
select * from musica where curtidas>= 6000;

desc musica;
truncate table musica;
select * from musica;
drop table musica;



-- TABELA 3 --
use sprint1;

create table filme (
idfilme int primary key,
titulo varchar (50),
genero varchar (40),
diretor varchar (40)
);

desc filme;

insert into filme values
(1, 'Gente Grande', 'Comedia', 'Dennis Dugan'),
(2, 'Vingadores', 'Ação', 'Joe Russo'),
(3, 'As Branquelas', 'Comedia', 'Keenen Ivory'),
(4, '2001', 'FicçãoC', 'Stanley Kubrick'),
(5, 'E.T.', 'FicçãoC', 'Steven Spielberg'),
(6, 'A viagem de Chihiro', 'Animação1', 'Hayao Miyazaki'),
(7, 'O Poderoso Chefão', 'Drama', 'Francis Ford');

select * from filme;

alter table filme add column protagonista varchar (50);
update filme set protagonista= 'Adam Sandler' where idfilme= 1;
update filme set protagonista= 'Homem de Ferro' where idfilme= 2;
update filme set protagonista= 'Marcus Copeland' where idfilme= 3;
update filme set protagonista= 'Dave Bowman' where idfilme= 4;
update filme set protagonista= 'E.T.' where idfilme= 5;
update filme set protagonista= 'Chihiro' where idfilme= 6;
update filme set protagonista= 'Michael Corleone' where idfilme= 7;
select * from filme;

alter table filme modify diretor varchar (150);
desc filme;

update filme set diretor= 'Roberto Carlos' where idfilme= 6;
update filme set diretor= 'Xuxa' where idfilme= 7 or idfilme= 5;
update filme set titulo= 'Brasileirão' where idfilme= 6;

delete from filme where idfilme= 3;

select * from filme where genero!= 'Comedia';
select * from filme where genero= 'FicçãoC';

desc filme;
truncate table filme;
drop table filme;

-- TABELA 4 -- 

use sprint1;

create table professores (
idprofessor int primary key,
nome varchar (40),
especialidade varchar (40),
dn date
);

desc professores;

insert into professores values
(1, 'Joao', 'p.i', '1974-02-28'),
(2, 'Roberto', 'algoritimo', '1966-11-04'),
(3, 'Maria', 'p.i', '2000-09-12'),
(4, 'Claudio', 's.o', '1980-01-20'),
(5, 'Antonio', 'socio.e', '1970-05-15'),
(6, 'Leticia', 'bancodedados', '1985-08-23');

select * from professores;
alter table professores add column função varchar (50);
update professores set função= 'Monitor' where idprofessor= 1;
update professores set função= 'Titular' where idprofessor= 2;
update professores set função= 'Assistente' where idprofessor= 3;
update professores set função= 'Monitor' where idprofessor= 4;
update professores set função= 'Titular' where idprofessor= 5;
update professores set função= 'Monitor' where idprofessor= 6;

insert into professores values 
(7, 'Ronaldo', 's.o', '2001-09-12', 'monitor');

delete from professores where idprofessor= 5; 

select nome from professores where função= 'Titular';
select especialidade from professores where função= 'Monitor';

update professores set dn= '1999-01-12' where idprofessor= 3;

truncate professores;






 



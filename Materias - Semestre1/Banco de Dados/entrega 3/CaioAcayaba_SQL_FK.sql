-- Prática 3:
create database Sprint2;
use Sprint2;

-- TABELA ATLETA --
create table atleta (
idatleta int primary key,
nome varchar (40),
modalidade varchar (40),
qntmedalha int
);

insert into atleta values
	('123', 'Pedro', 'volei', 10),
	('124', 'Marcelo', 'futebol', 6),
	('125', 'Gabriel', 'volei', 8),
	('126', 'Mateus', 'handball', 5);
    
-- TABELA PAÍS --
create table pais (
idPais int primary key,
nome varchar (30),
capital varchar (40)
);

insert into pais values
	('223', 'Brasil', 'Brasília'),
    ('224', 'China', 'Pequim'),
    ('225', 'Canadá', 'Ottawa'),
    ('226', 'Egito', 'Cairo');
    
-- Join:

alter table atleta add column fkPais int not null;
	update atleta set fkPais = 223 where idatleta = 123;
    update atleta set fkPais = 224 where idatleta = 124;
    update atleta set fkPais = 225 where idatleta = 125;
    update atleta set fkPais = 226 where idatleta = 126;
    
alter table atleta add constraint fkAtletaPais foreign key (fkPais) references pais (idPais); 
	select * from atleta join pais on idPais = fkPais;
    select atleta.nome as 'Nome', modalidade as 'Modalidade', qntmedalha as 'Medalhas', pais.nome as 'País', capital as 'Capital' from atleta join pais on idPais = fkPais;
    select atleta.nome as 'Nome', pais.nome as 'País' from atleta join pais on idPais = fkPais;
    select atleta.nome as 'Nome', modalidade as 'Modalidade', qntmedalha as 'Medalhas', pais.nome as 'País', capital as 'Capital' from atleta join pais on idPais = fkPais where capital = 'Pequim';

-- -------------------------------------------------------------------------------------------------------------------------------------------------------------

-- TABELA MUSICA --
create table musica (
idmusica int primary key,
titulo varchar (40),
artista varchar (40),
genero varchar (40)
);

insert into musica values
	(1, 'Samba', 'Lagum', 'Samba'),
	(2, 'Rosa Norte', 'Armandinho', 'Reggae'),
	(3, 'Manha', 'Teto', 'Trap'),
	(4, 'Dia Azul', 'Teto', 'Trap'),
	(5, 'Meu Lugar', 'Arlindo Cruz', 'Samba');
    
create table album (
idAlbum int primary key,
nome varchar (40),
tipo varchar (40), constraint chktipo check (tipo in ('Digital', 'Fisico')),
dtLançamento date
);

insert into album values 
	(11, 'Samba', 'Digital', '2019-03-21'),
    (12, 'Álbum Armandinho', 'Fisico', '2002-05-04'),
    (13, 'Prévias.zip', 'Digital', '2020-06-30'),
    (14, 'Prévias.zip', 'Digital', '2020-06-30'),
	(15, 'Meu Lugar', 'Fisico', '1990-02-14');
    
alter table musica add column fkAlbum int not null;
	update musica set fkAlbum = 11 where idmusica = 1;
	update musica set fkAlbum = 12 where idmusica = 2;
	update musica set fkAlbum = 13 where idmusica = 3;
	update musica set fkAlbum = 14 where idmusica = 4;
	update musica set fkAlbum = 15 where idmusica = 5;
    
alter table musica add constraint fkMusicaAlbum foreign key (fkAlbum) references album (idAlbum);	
	select * from musica join album on idAlbum = fkAlbum;
    select titulo as 'Titulo', artista as 'Artista', genero as 'Gênero', nome as 'Album', dtLançamento as 'Lançamento' from musica join album on idAlbum = fkAlbum;
    select titulo as 'Titulo', nome as 'Album' from musica join album on idAlbum = fkAlbum;
    select titulo as 'Titulo', artista as 'Artista', genero as 'Gênero', nome as 'Album', dtLançamento as 'Lançamento' from musica join album on idAlbum = fkAlbum where tipo = 'Digital';
    
    
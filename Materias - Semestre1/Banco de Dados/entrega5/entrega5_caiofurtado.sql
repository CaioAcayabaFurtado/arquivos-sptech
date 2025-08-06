create database entrega5;
use entrega5;

-- TABELA PET -- 
create table pet (
id_pet int primary key auto_increment,
nome varchar (45),
tipo varchar (45) check (tipo in ('cachorro', 'gato')),
raca varchar (45),
dtNasc date,
fk_cliente int,
	constraint FkClientePet foreign key (fk_cliente) references cliente (id_cliente)
) auto_increment = 101;

	insert into pet (nome, tipo, raca, dtNasc, fk_cliente) values
		('rex', 'cachorro', 'salsicha', '2024-02-15', 1),
		('tom', 'gato', 'persa', '2024-03-24', 3),
        ('pipoca', 'cachorro', 'vira-lata', '2005-02-10', 2),
        ('lua', 'gato', 'siames', '2023-12-25', 1);
	

-- TABELA CLIENTE -- 
create table cliente (
id_cliente int primary key auto_increment,
nome varchar (45),
telFixo varchar (45),
telCelular varchar (45),
cep char (9),
numero varchar (45),
complemento varchar (45)
) auto_increment = 1;

	insert into cliente (nome, telFixo, telCelular, cep, numero, complemento) values
		('Cleide Silva', '3653-4025', '11981765768', '123456789', '32', 'apto 20'),
        ('Jonas de Francisco', '3653-9020', '11999345263', '012345678', '10', 'apto 10'),
        ('Matheus Silva', '3653-3354', '11945862045', '234567890', '6', 'apto 06');

-- SELECT's -- 
select * from pet;
select * from cliente;
alter table cliente modify column nome varchar (60);
select * from pet where tipo = 'gato';
select nome, dtNasc from pet;
select * from pet order by nome;
select * from cliente order by complemento desc;
select * from cliente where nome like '%Silva';
update cliente set telFixo = '14988160614' where id_cliente = 2;
select pet.nome, tipo, raca, cliente.nome from pet 
	join cliente
		on fk_cliente = id_cliente;
select pet.nome, tipo, raca, cliente.nome from pet 
	join cliente
		on fk_cliente = id_cliente
			where id_cliente = 1;
delete from pet where id_pet = 105;
drop table pet;
drop table cliente;

-- ---------------------------------------------------------------------------------------------------------------------
-- ---------------------------------------------------------------------------------------------------------------------
-- ---------------------------------------------------------------------------------------------------------------------

-- TABELA PESSOA --
create table pessoa (
id_pessoa int primary key auto_increment,
nome varchar (45),
dtNasc date,
profissao varchar (45)
);
	
    insert into pessoa (nome, dtNasc, profissao) values
		('maria luiza', '1999-10-23', 'veterinaria'),
        ('pedro lucas', '2003-03-12', 'engenheiro civil');

-- TABELA GASTOS --
create table gasto (
id_gasto int primary key auto_increment,
dataa date,
valor varchar (45),
descricao varchar (90),
fk_pessoa int,
	constraint FkPessoaGasto foreign key (fk_pessoa) references pessoa (id_pessoa)
) auto_increment = 101;

	insert into gasto (dataa, valor, descricao, fk_pessoa) values
		('2024-04-12', 'R$2.000', 'conserto do carro', 1),
		('2024-02-14', 'R$190', 'ingresso do show', 2);
    
    
-- SELECT's -- 

select * from pessoa;
select * from gasto;
select * from pessoa where profissao = 'veterinaria';
select nome, profissao, valor, dataa from pessoa
	join gasto
		on fk_pessoa = id_pessoa;
update gasto set valor = 'R$400' where id_gasto = 102;
delete from gasto where id_gasto = 101;
drop table pessoa;
drop table gasto;

-- ---------------------------------------------------------------------------------------------------------------------
-- ---------------------------------------------------------------------------------------------------------------------
-- ---------------------------------------------------------------------------------------------------------------------

-- TABELA SETOR --
create table setor (
id_setor int primary key,
nome varchar (45),
numAndar varchar (45)
);

	insert into setor values
		(10,'financeiro', '10'),
        (11, 'executivo', '11');

-- TABELA FUNCIONARIO -- 
create table funcionario (
id_funcionario int primary key auto_increment,
nome varchar (45),
salario varchar (45),
telefone varchar (45),
fk_setor int,
	constraint FkSetorFuncionario foreign key (fk_setor) references setor (id_setor)
)auto_increment = 1;
	
    insert into funcionario (nome, salario, telefone, fk_setor) values
		('roberto', 'R$20.000', '11934758394', 11),
        ('fernando', 'R$6.000', '11934855294', 10),
        ('julia', 'R$6.000', '11938475628', 10);

-- TABELA ACOMPANHANTE --
create table acompanhante (
id_acompanhante int,
fk_funcionario int,
	constraint pkCompostaAcompanhante primary key (id_acompanhante, fk_funcionario),
nome varchar (45),
relacao varchar (45),
dtNasc date
);

	insert into acompanhante values
		(1, 1, 'marilda', 'esposa', '1980-06-12'),
        (2, 1, 'lucas', 'filho', '2009-11-20'),
        (1, 3, 'carlos', 'namorado', '2005-08-03');
        
-- SELECT's -- 

select * from setor; 
select * from funcionario;
select * from acompanhante;
select funcionario.nome, salario, telefone, setor.nome as setor from funcionario
	join setor
		on fk_setor = id_setor;
select setor.nome, numAndar, funcionario.nome as funcionario from setor
	join funcionario
		on id_setor = fk_setor
			where setor.nome = 'financeiro';
select funcionario.nome, salario, telefone, acompanhante.nome from funcionario
	join acompanhante
		on fk_funcionario= id_funcionario;
-- sim, o fernando é um cara solitário
select funcionario.nome, acompanhante.nome, relacao from funcionario
	join acompanhante
		on fk_funcionario = id_funcionario
			where funcionario.nome = 'julia';
select funcionario.nome, salario, setor.nome, acompanhante.nome, relacao from funcionario
	join setor
		on fk_setor = id_setor
	join acompanhante
		on fk_funcionario = id_funcionario;
-- o fernando não apareceu :(
drop table acompanhante;
drop table funcionario;
drop table setor;

-- ---------------------------------------------------------------------------------------------------------------------
-- ---------------------------------------------------------------------------------------------------------------------
-- ---------------------------------------------------------------------------------------------------------------------

-- TABELA TREINADOR -- 
create table treinador (
id_treinador int primary key auto_increment,
nome varchar (45),
telefone varchar (45),
email varchar (45),
fk_experiente int,
	constraint FkExperienteTreinador foreign key (fk_experiente) references treinador (id_treinador)
) auto_increment = 10;

	insert into treinador (nome, telefone, email, fk_experiente) values
		('fabio', '110394858274', 'fabio@gmail.com', null),
        ('marcelo', '11837465937', 'marcelo@gmail.com', 10),
        ('raissa', '11938475947', 'raissa@gmail.com', 10);

-- TABELA NADADOR --
create table nadador (
id_nadador int primary key auto_increment,
nome varchar (45),
estado varchar (45),
dtNasc date,
fk_treinador int,
	constraint FkTreinadorNadador foreign key (fk_treinador) references treinador (id_treinador)
) auto_increment = 100;

	insert into nadador (nome, estado, dtNasc, fk_treinador) values
		('lucas', 'SP', '2000-12-20', 12),
        ('gustavo', 'MG', '2002-01-25', 10),
        ('pedro', 'BH', '2000-05-01', 12),
        ('ronaldo', 'SP', '1999-07-29', 11);
        
-- SELECT's --

select treinador.nome, telefone, email, nadador.nome from treinador
	join nadador
		on fk_treinador = id_treinador
			where id_treinador = 12;
select treinador.nome, treinador.telefone, treinador.email, orientador.nome from treinador
	join treinador as orientador
		on treinador.fk_experiente = orientador.id_treinador;
select treinador.nome, treinador.telefone, treinador.email, 
orientador.nome as orientador, orientador.telefone, orientador.email, 
nadador.nome as nadador, estado from treinador
	join treinador as orientador
		on treinador.fk_experiente = orientador.id_treinador
	join nadador
		on fk_treinador = treinador.id_treinador;
drop table nadador;
drop table treinador;
        


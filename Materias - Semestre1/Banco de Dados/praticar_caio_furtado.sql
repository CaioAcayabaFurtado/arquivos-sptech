create database praticar;
use praticar;

-- TABELA PET --
create table pet (
id_pet int primary key auto_increment,
nome varchar (45),
fk_maepet int,
	constraint FkMaePet foreign key (fk_maepet) references pet (id_pet)
);

insert into pet (id_pet, nome) values
	(default, "garfield"),
    (default, "marry");
    
update pet set fk_maepet = 2 where id_pet = 1;

select pet2.nome as Nome, pet1.nome as Mâe 
	from pet as pet1 join pet as pet2
		on pet1.id_pet = pet2.fk_maepet;
        
select * from pet;

-- ----------------------------------------------------------------------------------------------------------------------------------------------------
-- ----------------------------------------------------------------------------------------------------------------------------------------------------
-- ----------------------------------------------------------------------------------------------------------------------------------------------------
-- ----------------------------------------------------------------------------------------------------------------------------------------------------
-- ----------------------------------------------------------------------------------------------------------------------------------------------------

-- 	TABELA FUNCIONARIO --
create table funcionario (
id_funcionario int primary key auto_increment,
nome varchar (45),
fk_supervisor int,
	constraint FkSupervisor foreign key (fk_supervisor) references funcionario (id_funcionario),
fk_empresa int,
	constraint FkEmpresa foreign key (fk_empresa) references empresa (id_empresa)
) auto_increment = 10;

insert into funcionario (id_funcionario, nome) values
	(default, "Claudio"),
	(default, "Sergio"),
	(default, "Vitoria");
    
update funcionario set fk_supervisor = 11 where id_funcionario in (10,12);
update funcionario set fk_empresa = 30 where id_funcionario in (10, 11, 12);

-- ----------------------------------------------------------------------------------------------------------------------------------------------------

select * from funcionario;

select Funcio.nome as Nome, Supervisor.nome
	from funcionario as Supervisor join funcionario as Funcio
		on Supervisor.id_funcionario = Funcio.fk_supervisor;
        
select Funcio.nome as Nome, Supervisor.nome, empresa.nome
	from funcionario as Supervisor join funcionario as Funcio
		on Supervisor.id_funcionario = Funcio.fk_supervisor,
			funcionario join empresa 
				on id_empresa = fk_empresa;
-- Não sei como mas os nomes triplicaram...

-- ----------------------------------------------------------------------------------------------------------------------------------------------------

-- TABELA EMPRESA --

create table empresa (
id_empresa int primary key auto_increment,
nome varchar (45)
) auto_increment = 30;

insert into empresa (nome) value 
	("Spotify");
    
-- ----------------------------------------------------------------------------------------------------------------------------------------------------
-- ----------------------------------------------------------------------------------------------------------------------------------------------------
-- ----------------------------------------------------------------------------------------------------------------------------------------------------
-- ----------------------------------------------------------------------------------------------------------------------------------------------------
-- ----------------------------------------------------------------------------------------------------------------------------------------------------

-- TABELA PESSOA --

create table pessoa (
id_pessoa int primary key auto_increment,
nome varchar (45)
);

insert into pessoa (nome) values
	("pessoa1"),
    ("pessoa2");
    
-- ----------------------------------------------------------------------------------------------------------------------------------------------------

-- TABELA SOMBRA --
create table sombra (
fk_pessoa int,
	constraint FkSombra foreign key (fk_pessoa) references pessoa (id_pessoa)
);

insert into sombra value 
	(1),
    (2);
    
-- ----------------------------------------------------------------------------------------------------------------------------------------------------

-- TABELA ENDEREÇO --
create table logradouro (
endereco varchar (45),
fk_pessoa2 int,
	constraint fkPessoa2 foreign key (fk_pessoa2) references pessoa (id_pessoa)
);

insert into logradouro values
	("Rua bablabla, 100", 1),
    ("Rua pipipi, 200", 2);
    
-- ----------------------------------------------------------------------------------------------------------------------------------------------------

select pessoaaa.nome as Nome, sombra.fk_pessoa as Sombra, logradouro.endereco as Endereço
	from pessoa as pessoaaa join sombra
		on pessoaaa.id_pessoa = fk_pessoa,
			pessoa as pessoaa join logradouro
				on pessoaa.id_pessoa = fk_pessoa2;
-- A coluna "pessoa duplicou" 
                

    

    
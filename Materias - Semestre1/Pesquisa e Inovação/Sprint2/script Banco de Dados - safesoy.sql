create database safesoy;

use safesoy;

create table fazenda(
idFazenda int primary key auto_increment,
nome varchar (45),
CNPJ varchar (45),
cep char (9),
numero varchar (45),
cidade varchar (45),
estado varchar (45)
);

create table setor (
idSetor int primary key auto_increment,
descricao varchar (45),
fkFazenda int,
CONSTRAINT fkSetorFazenda FOREIGN KEY (fkFazenda) REFERENCES fazenda (idFazenda));

create table dados (
idDados int primary key auto_increment,
umidade decimal (5,2),
temperatura decimal (5,2),
dtRegistrada date
);

create table usuario (
RF int primary key auto_increment,
nome varchar (45),
sobrenome varchar(45),
email varchar (265),
senha varchar (45),
fkFazenda int,
constraint fkUsuarioFazendad foreign key (fkFazenda) references fazenda (idFazenda),
fkSuperFuncionario int,
constraint fkUsuarioSuperFuncionario foreign key (fkSuperfuncionario) references usuario (RF)
);

use safesoy;
select * from dados;
truncate table dados;




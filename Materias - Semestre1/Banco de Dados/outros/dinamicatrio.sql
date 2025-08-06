create database trabalhotrio;
use trabalhotrio;

-- um aplicativo de comida desenvolve um sistema para salvar o endereço do usuário em sua conta 
create table endereço (
idendereco int primary key auto_increment,
estado char (2),
cidade varchar (50),
rua varchar (50),
numero int 
) auto_increment = 1;

-- Em um dos testes, para pedir uma pizza Rodrigão precisava preencher os campos do endereço
insert into endereço values 
	(default, 'SP', 'Sâo Paulo', 'Hadock Lobo', 20);
select * from endereço;

-- porém Rodrigão mora em apartamento e percebe que o sistema não tem um campo de "complemento", assim relata aos seus colegas de trabalho sobre o problema
alter table endereço add column complemento varchar (50);
update endereço set complemento = 'apto 24' where idendereco= 1;
select * from endereço;

-- No segundo teste deu tudo certo, porém o visual do projeto não estava dos melhores... assim para finalizar uma solução surgiu:
select concat('O endereço foi inserido: Rua ',rua,', ', numero, ', ', cidade, '(', estado, ')', '. Complemento: ', complemento) as Frase from endereço;

drop table endereço;
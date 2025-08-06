-- criar o banco de dados :)
create database sisa;

-- selecionar o banco de dados
use sisa;

-- criar primeira tabela
create table aluno (
ra char(8) primary key,
nome varchar(100),
bairro varchar(45)
);

-- descrever os campos da tabela
 desc aluno;
 
 -- inseção de dados
 insert into aluno values
 ('03241026', 'Caio', 'Paraíso');
 
 insert into aluno values
 ('03241057', 'Fabio', 'Bela Vista');
 
  insert into aluno values
 ('03241028', 'Betina', 'Jardins');
 
 -- exibir tabela
 select ra, nome, bairro from aluno;
 
 -- exibir apenas fabio
 select nome from aluno
 where nome = 'Fabio';
 
 -- exibir apenas alunos onde o nome dos alunos terminam em O 
 select * from aluno where nome like '%o';
 
  -- exibir apenas alunos onde bairro contem ''vis''
 select * from aluno where bairro like '%Vis%';
 
 -- ordenar em ordem alfabetica
 
 select * from aluno order by nome;
 
  -- ordenar em ordem alfabetica dec
 
 select * from aluno order by nome desc;
 
 -- exibir o bairro onde a segunda letra é ''e''
 select * from aluno where bairro like '_e%';
 
 -- exibir aluno q comeca com o nome q comeca com a letra c e o bairro comtem a 
 select * from aluno
 where nome like 'C%' and bairro like '%a%';
 
	-- excluir tabela: drop table aluno
 
 
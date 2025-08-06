use sisa;

-- 	ENTIDADE FORTE: Pk simples -- 
create table funcionario (
id_funcionario int primary key auto_increment,
nome varchar (45), -- atrb. simples
cep char (9), -- atrb. composto: cep + num + complem
numEnd varchar (45), --
complemento varchar (45), --
telCel varchar (45), -- atrb. multivalorado
telFixo varchar (45), --

fk_supervisor int,
	constraint FkSupervisorFuncionario foreign key (fk_supervisor) references funcionario (id_funcionario)
);

-- inserir um supervisor 
	insert into funcionario values
		(default, 'canhoto', '01414-905', '595', '11°andar', '11 987654321', '11 54321122', null);
-- insert funcionarios
	insert into funcionario (nome, fk_supervisor) values
		('destro', 1),
        ('Ambidestro', 1);
        
select func.nome as funcionario, sup.nome as supervisor from funcionario as func
	join funcionario as sup
		on func.fk_supervisor = sup.id_funcionario;
	


-- ENTIDADE FRACA: pk composta --
create table dependente (
id_dependente int,
fk_funcionario int,
	constraint PkCompostaDependente primary key (id_dependente, fk_funcionario),
    
    constraint FkFuncionarioDependente foreign key (fk_funcionario) references funcionario (id_funcionario),
    
nome varchar (45),   
parentesco varchar (45),
dtNasc date
);

insert into dependente values
	(1, 2, 'destrinho', 'filho', '2004-11-23'),
    (2, 2, 'destra', 'esposa', '1960-03-12'),
    (1, 3, 'ambidestrinha', 'filha', '2005-02-27');
    
select funcionario.nome as funcionario, dependente.nome as dependente from funcionario
	join dependente 
		on fk_funcionario = id_funcionario;
        
select funcionario.nome as funcionario, supervisor.nome as supervisor, dependente.nome as dependente from funcionario as funcionario
	join funcionario as supervisor
			on funcionario.fk_supervisor = supervisor.id_funcionario
	join dependente
		on fk_funcionario = funcionario.id_funcionario;
        
-- --------------------------------------------------------------------------
-- EXIBIT TODOS OS FUNCIONARIOS INCLUSIVE OS QUE NAO TEM DEPENDENTES
select funcionario.nome as funcionario, dependente.nome as dependente 
	from funcionario left join dependente 
		on fk_funcionario = id_funcionario;
-- ou
select funcionario.nome as funcionario, dependente.nome as dependente 
	from dependente right join funcionario
		on fk_funcionario = id_funcionario;
        
-- SO O QUE NAO TEM DEPENDENTE
select funcionario.nome as funcionario, dependente.nome as dependente 
	from dependente right join funcionario
		on fk_funcionario = id_funcionario
			where fk_funcionario is null;
            
-- PROVA

select concat ('o nome é ',nome, ' e seu endereco é ', ifnull(numEnd, 'Sem número'), ifnull(complemento, 'Sem complemento')) as frase from funcionario;

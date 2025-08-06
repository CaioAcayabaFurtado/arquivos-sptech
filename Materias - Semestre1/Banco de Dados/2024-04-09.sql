-- TIPOS DE ATRIBUTOS

-- atributo identificador (primary key)
-- atributo simples (nao se divide em subpartes)
	-- exemplo: nome completo
-- atributo composto - deve ser dividido em subpartes
	-- exemplo: endereco (cep/numero/complemento)
-- atributo MONOvalorado: ele tem apenas 1 valor
	-- exemplo: Bairro
-- atributos MULTIvalorado
	-- telefone (celular e fixo)
	-- email (pessoal e proficional)
-- atributo derivado: idade
	-- deriva da dtNasc
    
use sisa; 
    
create table funcionario (
id_funcionario int primary key, -- atributo identificador
nome varchar (45), -- atributo simples
cep char (9), -- atributo composto: cep + num + complemento
numEnd varchar (45),
complemento varchar (45),
telCelular varchar (45), -- atributo multivalorado: telCelular + telFixo
telFixo varchar (45)
);

insert into funcionario values
	(1, 'Picapau', '01414-905', '595', '11andar', '11 987654322', '11 230947520'),
	(2, 'Papai Smurf', '01234-935', '509', '17andar', '11 123098439', '11 090123847'),
	(3, 'Bob  Esponja', '01333-432', '595', '13andar', '11 982344322', '11 23094723');


    
-- ------------------------------------------------------------------------
    
create table dependente (
id_dependente int, -- chave primaria composta
fk_funcionario int,
	constraint pkComposta primary key (id_dependente, fk_funcionario),
    constraint fk_funcio_dep foreign key (fk_funcionario) references funcionario (id_funcionario),
    
nome varchar (45),
parentesco varchar (45),
dtNasc date
);

insert into dependente values
-- pk1 + pk2 = id_dependente + fk_funcionario
	(1, 1, 'Zeca', 'Pai', '1990-01-01'),
	(2, 1, 'Leoncio', 'Avô', '1930-01-01'),
	(1, 2, 'Mamãe Smurf', 'Esposa', '1967-01-01');
    -- o conjunto dos id's não se repetem
    
    
select * from funcionario 
		 join dependente
		 on fk_funcionario = id_funcionario;
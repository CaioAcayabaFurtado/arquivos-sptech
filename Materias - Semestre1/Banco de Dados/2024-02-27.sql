-- aula 27/02/2006

drop database revisao;
create database revisao;
use revisao;

create table produto (
	idproduto int primary key auto_increment,
    nome varchar (50),
    validade date,
    categoria varchar (50),
    codigodebarras char (13),
    quantidade int
    );
    
    desc produto;
    
    insert into produto values
		(default, 'garrafa de agua', '2024-08-27', 'alimenticio', '8934698725333', 5);
    insert into produto values
		(default, 'carregador USB-C', null, 'eletronico', '0981230981230', 9);
	select * from produto;
    insert into produto values 
		(default, 'camisa polo', null, 'vestuario', '4560984560984', 8);
    insert into produto values 
		(default, 'miojo', '2025-02-27', 'alimenticio', '1230981230981', 12);
    insert into produto (nome, validade, categoria, codigodebarras, quantidade)values
		('maca', '2024-03-15', 'alimenticio', '1238761238764', 15);
        
select * from produto where nome like 'c%';
select * from produto where nome like 'm%a';
select * from produto where nome like '_a%';
    
alter table produto add column empresa varchar (50);
alter table produto rename column empresa to marca;
select * from produto;

update produto set marca= 'fazenda do vovo' where idproduto= 5;
update produto set quantidade= 30 where idproduto in (2,1);
update produto set quantidade= 40  where idproduto= 4 or idproduto= 5;

alter table produto drop column marca;
desc produto;

alter table produto add constraint chkcategoria check (categoria in ('alimenticio', 'eletronico', 'vestuario'));
alter table produto add  check (categoria in ('alimenticio', 'eletronico', 'vestuario', 'material escolar'));

select * from produto;
delete from produto where idproduto= 5;

truncate table produto;
drop table produto;

	


    
    

    
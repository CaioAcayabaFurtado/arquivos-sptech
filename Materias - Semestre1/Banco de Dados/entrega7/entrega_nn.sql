create database venda;
use venda;
	drop table cliente;
	drop table venda;
	drop table produto;
	drop table sistema;
    
-- TABELA CLIENTE -- 
create table cliente(
id_cliente int primary key auto_increment,
nome varchar (45),
email varchar(45),
endereco varchar (90),

fk_indicador int,
	constraint FkIndicadorCliente foreign key (fk_indicador) references cliente (id_cliente)
) auto_increment = 1;

	insert into cliente (nome, email, endereco, fk_indicador) values
		('Eduardo','eduardo@gmail.com','Rua tralala, bairro do chapeu', null),
        ('Maria','maria@gmail.com','Rua numsei, bairro bela vista', 1),
		('João','joao@gmail.com','Rua fulano, bairro ciclano', 1);
    
-- TABELA VENDA -- 
create table venda(
id_venda int primary key auto_increment,
dataa date,
total decimal (10,2),

fk_cliente int,
	constraint FkClienteProduto foreign key (fk_cliente) references cliente (id_cliente)
) auto_increment = 100;

	insert into venda (dataa, total, fk_cliente) values
		('2024-04-25', null, 1),
        ('2024-06-02', null, 2);
        
-- TABELA PRODUTO -- 
create table produto(
id_produto int primary key auto_increment,
nome varchar (45),
descricao varchar (90),
valor decimal (10,2)
) auto_increment = 200;

	insert into produto (nome, descricao, valor) values
		('veja', 'Produto de limpeza', '30'),
        ('nescau', 'Achocolatado em pó', '20');
        
-- TABELA SISTEMA --

create table sistema(
fk_produto int,
fk_venda int,
	constraint FkProdutoSistema foreign key (fk_produto) references produto (id_produto),
    constraint FkVendaSistema foreign key (fk_venda) references venda (id_venda),
    constraint primary key (fk_produto, fk_venda),
    
quantidade int,
desconto decimal (10,2)
);

	insert into sistema values
		(200, 101, 2, 10.00),
        (201, 100, 3, null);
 
 -- ------------------------------------------------------------------------------------------------------------------------------------------------
 
-- SELECT's:

select * from cliente;
select * from venda;
select * from produto;
select * from sistema;

select nome, email, endereco, dataa, total from cliente
	join venda
		on id_cliente = fk_cliente;
        
select nome, email, endereco, dataa, total from cliente
	join venda
		on id_cliente = fk_cliente
			where nome = 'Eduardo';
            
select cliente.nome, email, endereco, dataa, total, produto.nome, descricao, valor from cliente
	join venda
		on fk_cliente = id_cliente
    join sistema
		on id_venda = fk_venda
	join produto
		on fk_produto = id_produto;
        
select dataa, nome, quantidade from venda
	join sistema
		on id_venda = fk_venda
    join produto 
		on id_produto = fk_produto
	where id_venda = 100;
        
select nome, valor, SUM(quantidade) from produto
	join sistema
		on id_produto = fk_produto
			group by nome, valor;

insert into cliente (nome) value
	('Gabriel');
select nome, email, endereco, dataa, total from cliente
	left join venda
		on id_cliente = fk_cliente;

-- valor minimo e maximo
select min(valor), max(valor) from produto;
-- soma e média do valor
select sum(valor), avg(valor) from produto;
-- quantidade de preços acima da média
select count(valor) from produto
	where valor > (select avg(valor) from produto);
-- soma de preços distintos
select sum(distinct valor) from produto;
-- soma dos preços dos produtos agrupado por uma determinada venda. / TOTAL DO VALOR DA VENDA
select id_venda, sum(valor) from venda
	join sistema
		on fk_venda = id_venda
    join produto
		on id_produto = fk_produto
	group by id_venda;
        



	





















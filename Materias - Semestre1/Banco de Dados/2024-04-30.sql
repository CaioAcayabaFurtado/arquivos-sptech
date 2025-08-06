create database aulas;
use aulas;

create table paciente (
id_paciente int primary key auto_increment,
nome varchar (45)
);

insert into paciente values
	(default, 'Florinda'),
	(default, 'Chaves');

create table medico (
id_medico int primary key auto_increment,
nome varchar (45)
) auto_increment = 100;

insert into medico values
	(default, 'Picapau'),
	(default, 'Chapeuzinho Vermelho');

create table consulta (
id_consulta int auto_increment,
fk_medico int,
fk_paciente int,
	-- RELAÇÃO N PRA M --
	constraint primary key (fk_paciente, fk_medico, id_consulta),
dt_consult datetime
)auto_increment = 50;
drop table consulta;
insert into consulta values
	(default, 100, 1, '2024-04-30 08:00:00'),
	(default, 101, 1, '2024-04-30 10:00:00'),
	(default, 100, 2, '2024-04-30 10:00:00'),
	(default, 101, 2, '2024-04-30 8:00:00');
    
    
select * from paciente join consulta
	on idPaciente = fkPaciente
    join medico on idMedico = fkMedico;
    
select paciente.nome as Paciente,
	medico.nome as Medico,
    dtConsulta
	from paciente join consulta
	on idPaciente = fkPaciente
    join medico on idMedico = fkMedico;
    
select paciente.nome as Paciente,
	medico.nome as Medico,
    dtConsulta
	from paciente join consulta
	on idPaciente = fkPaciente
    join medico on idMedico = fkMedico
    where medico.nome = 'Picapau';


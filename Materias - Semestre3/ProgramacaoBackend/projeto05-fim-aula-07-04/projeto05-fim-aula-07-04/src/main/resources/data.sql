-- As instruções DML (insert, update e delete)
-- aqui serão executadas quando a aplicação iniciar
-- PODEM haver quantas forem necessárias.
-- apenas separe elas com ponto e vírgula.

-- MAAAAS, para que funcione, é necessária a seguinte configuração no application.properties:
-- spring.jpa.defer-datasource-initialization=true

INSERT INTO musica (nome, interprete, data_cadastro, propria_para_criancas, quantidade_reproducoes, foto)
VALUES
('Garota Ipanema', 'Tom Jobim', '2023-10-01', true, 100, NULL),
('Evidências', 'Chitãozinho', '2023-10-02', true, 200, NULL),
('Aquarela', 'Toquinho', '2023-10-03', true, 150, NULL),
('Imagine', 'John Lennon', '2023-10-04', false, 300, NULL),
('Bohemian Rhap', 'Queen', '2023-10-05', false, 400, NULL);
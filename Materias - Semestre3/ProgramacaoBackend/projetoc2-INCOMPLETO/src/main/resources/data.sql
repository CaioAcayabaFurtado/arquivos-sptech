INSERT INTO pais (codigo, nome) VALUES
    (1, 'Brasil'),
    (2, 'Estados Unidos'),
    (3, 'Filipinas'),
    (4, 'Irlanda');

INSERT INTO categoria (codigo, nome) VALUES
    (1, 'Peso Mosca'),
    (2, 'Peso Galo'),
    (3, 'Peso Pena'),
    (4, 'Peso Leve'),
    (5, 'Peso Meio-Médio'),
    (6, 'Peso Médio'),
    (7, 'Meio-Pesado'),
    (8, 'Peso Pesado');

INSERT INTO pugilista (
    nome, categoria_codigo, pais_codigo, peso, vitorias_nocaute, vitorias_pontos, empates, derrotas_nocaute, derrotas_pontos, aposentado
) VALUES
      ('Anderson Silva', 6, 1, 84.0, 23, 4, 0, 2, 1, false),
      ('Anderson Silva Jr', 5, 1, 84.0, 2, 0, 0, 2, 1, false),
      ('Mike Tyson', 8, 2, 100.0, 44, 5, 0, 6, 0, true),
      ('Manny Pacquiao', 4, 3, 66.7, 39, 23, 2, 3, 2, false),
      ('Ronda Rousey', 3, 2, 61.2, 12, 0, 0, 2, 0, true),
      ('Conor McGregor', 2, 4, 70.3, 19, 1, 0, 4, 0, false),
      ('Floyd Mayweather', 4, 2, 68.0, 27, 23, 0, 0, 0, true),
      ('Amanda Nunes', 1, 1, 65.8, 13, 4, 0, 2, 0, false);
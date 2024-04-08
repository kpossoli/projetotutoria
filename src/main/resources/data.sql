INSERT INTO professores (nome)
VALUES ('Professor A'),
       ('Professor B'),
       ('Professor C');

INSERT INTO alunos (nome, nascimento)
VALUES ('Aluno 1', '2003-01-01'),
       ('Aluno 2', '2003-02-02'),
       ('Aluno 3', '2003-03-03'),
       ('Aluno 4', '2003-04-04');

INSERT INTO disciplinas (nome, professor_id)
VALUES ('Matemática', 1),
       ('História', 2),
       ('Ciências', 3),
       ('Geografia', 1);

-- Correção: Inclua apenas a inserção correta com data_matricula
-- INSERT INTO disciplina_matriculas (aluno_id, disciplina_id, media_final, data_matricula) VALUES
-- (1, 1, 0.0, CURRENT_DATE),
-- (2, 2, 0.0, CURRENT_DATE),
-- (3, 3, 0.0, CURRENT_DATE);

-- INSERT INTO notas (disciplina_matricula_id, professor_id, nota, coeficiente) VALUES
-- (1, 1, 0.0, 0.000000),
-- (1, 1, 0.0, 0.000000),
-- (2, 2, 0.0, 0.000000),
-- (3, 3, 0.0, 0.000000),
-- (3, 1, 0.0, 0.000000);
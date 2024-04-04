CREATE TABLE IF NOT EXISTS professores (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL
);

CREATE TABLE IF NOT EXISTS alunos (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    nascimento DATE
);

CREATE TABLE IF NOT EXISTS disciplinas (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    professor_id BIGINT NOT NULL,
    FOREIGN KEY (professor_id) REFERENCES professores(id)
);

CREATE TABLE IF NOT EXISTS disciplina_matriculas (
    id BIGSERIAL PRIMARY KEY,
    aluno_id BIGINT NOT NULL,
    disciplina_id BIGINT NOT NULL,
    data_matricula DATE NOT NULL DEFAULT CURRENT_DATE,
    media_final NUMERIC(5, 2) NOT NULL DEFAULT 0.00,
    FOREIGN KEY (aluno_id) REFERENCES alunos(id),
    FOREIGN KEY (disciplina_id) REFERENCES disciplinas(id)
);

CREATE TABLE IF NOT EXISTS notas (
    id BIGSERIAL PRIMARY KEY,
    disciplina_matricula_id BIGINT NOT NULL,
    professor_id BIGINT NOT NULL,
    nota NUMERIC(5, 2) NOT NULL DEFAULT 0.00,
    coeficiente NUMERIC(19, 6) NOT NULL DEFAULT 0.00,
    FOREIGN KEY (disciplina_matricula_id) REFERENCES disciplina_matriculas(id),
    FOREIGN KEY (professor_id) REFERENCES professores(id)
);

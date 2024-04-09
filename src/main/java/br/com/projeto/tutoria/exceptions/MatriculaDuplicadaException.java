package br.com.projeto.tutoria.exceptions;

public class MatriculaDuplicadaException extends RuntimeException {

    public MatriculaDuplicadaException(Long alunoId, Long disciplinaId) {
        super("Aluno com ID " + alunoId + " já está matriculado na disciplina com ID " + disciplinaId + ".");
    }
}

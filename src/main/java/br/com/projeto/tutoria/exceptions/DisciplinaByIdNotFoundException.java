package br.com.projeto.tutoria.exceptions;

public class DisciplinaByIdNotFoundException extends RuntimeException {
    private final Long disciplinaId;

    public DisciplinaByIdNotFoundException(Long id) {
        super("Disciplina não encontrada com id" + id);
        this.disciplinaId = id;
    }

    public Long getDisciplinaId() {
        return disciplinaId;
    }
}
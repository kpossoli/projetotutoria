package br.com.projeto.tutoria.exceptions;

public class ProfessorByIdNotFoundException extends RuntimeException {
    private final Long professorId;

    public ProfessorByIdNotFoundException(Long id) {
        super("Professor não encontrado com id" + id);
        this.professorId = id;
    }

    public Long getProfessorId() {
        return professorId;
    }
}

package br.com.projeto.tutoria.exceptions;

public class DisciplinaMatriculaByIdNotFoundException extends RuntimeException {
    private final Long MatriculaId;

    public DisciplinaMatriculaByIdNotFoundException(Long id) {
        super("Matrícula não encontrada com id" + id);
        this.MatriculaId = id;
    }

    public Long getDisciplinaMatriculaId() {
        return MatriculaId;
    }
}

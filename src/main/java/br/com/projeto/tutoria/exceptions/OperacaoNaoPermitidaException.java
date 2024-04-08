package br.com.projeto.tutoria.exceptions;

public class OperacaoNaoPermitidaException extends RuntimeException {
    private final Long id;

    public OperacaoNaoPermitidaException(Long id) {
        super("Existem notas lançadas para a matrícula com id: " + id + ", não é possível excluir.");
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

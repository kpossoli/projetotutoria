package br.com.projeto.tutoria.exceptions;

public class ProfessorNotFoundException extends RuntimeException {

    public ProfessorNotFoundException(Long id) {
        super("Professor não encontrado com id: " + id);
    }
}

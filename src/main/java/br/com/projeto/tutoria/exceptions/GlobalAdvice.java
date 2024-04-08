package br.com.projeto.tutoria.exceptions;

import br.com.projeto.tutoria.exceptions.dto.Erro;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handler(Exception e) {
        Erro erro = Erro.builder()
                .codigo("500")
                .mensagem(e.getMessage())
                .build();
        return ResponseEntity.status(500).body(erro);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handler(DataIntegrityViolationException e) {
        Erro erro = Erro.builder()
                .codigo("400")
                .mensagem(e.getMessage())
                .build();
        return ResponseEntity.status(400).body(erro);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handler(NotFoundException e) {
        Erro erro = Erro.builder()
                .codigo("404")
                .mensagem(e.getMessage())
                .build();
        return ResponseEntity.status(404).body(erro);
    }

    @ExceptionHandler(AlunoByIdNotFoundException.class)
    public ResponseEntity<?> handler(AlunoByIdNotFoundException e) {
        String mensagem = "Aluno não encontrado com id: " + e.getAlunoId();
        Erro erro = Erro.builder()
                .codigo("404")
                .mensagem(mensagem)
                .build();
        return ResponseEntity.status(404).body(erro);
    }

    @ExceptionHandler(ProfessorByIdNotFoundException.class)
    public ResponseEntity<?> handler(ProfessorByIdNotFoundException e) {
        String mensagem = "Professor não encontrado com id: " + e.getProfessorId();
        Erro erro = Erro.builder()
                .codigo("404")
                .mensagem(mensagem)
                .build();
        return ResponseEntity.status(404).body(erro);
    }

    @ExceptionHandler(DisciplinaByIdNotFoundException.class)
    public ResponseEntity<?> handler(DisciplinaByIdNotFoundException e) {
        String mensagem = "Disciplina não encontrada com id: " + e.getDisciplinaId();
        Erro erro = Erro.builder()
                .codigo("404")
                .mensagem(mensagem)
                .build();
        return ResponseEntity.status(404).body(erro);
    }

    @ExceptionHandler(DisciplinaMatriculaByIdNotFoundException.class)
    public ResponseEntity<?> handler(DisciplinaMatriculaByIdNotFoundException e) {
        String mensagem = "Matrícula em disciplina não encontrada com id: " + e.getDisciplinaMatriculaId();
        Erro erro = Erro.builder()
                .codigo("404")
                .mensagem(mensagem)
                .build();
        return ResponseEntity.status(404).body(erro);
    }

    @ExceptionHandler(OperacaoNaoPermitidaException.class)
    public ResponseEntity<?> handleOperacaoNaoPermitida(OperacaoNaoPermitidaException e) {
        String mensagem = "Existem notas lançadas para esta matrícula, não é possível excluir.";
        Erro erro = Erro.builder()
                .codigo("422") // Usando o código 422 Unprocessable Entity
                .mensagem(mensagem)
                .build();
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(erro);
    }
}
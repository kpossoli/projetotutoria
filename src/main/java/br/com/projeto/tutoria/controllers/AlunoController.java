package br.com.projeto.tutoria.controllers;


import br.com.projeto.tutoria.entities.AlunoEntity;
import br.com.projeto.tutoria.services.AlunoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("alunos")
public class AlunoController {

    private final AlunoService service;

    @GetMapping
    public ResponseEntity<List<AlunoEntity>> buscarTodos() {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarTodos());
    }

    @GetMapping("{id}")
    public ResponseEntity<AlunoEntity> buscarTodos(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<AlunoEntity> criarAluno(@RequestBody AlunoEntity aluno) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(aluno));
    }

    @PutMapping("{id}")
    public ResponseEntity<AlunoEntity> alterarAluno(@PathVariable Long id, @RequestBody AlunoEntity aluno) {
        return ResponseEntity.status(HttpStatus.OK).body(service.alterar(id, aluno));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarAluno(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }


}

package br.com.projeto.tutoria.controllers;


import br.com.projeto.tutoria.entities.ProfessorEntity;
import br.com.projeto.tutoria.services.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("professores")
public class ProfessorController {

    private final ProfessorService service;

    @GetMapping
    public ResponseEntity<List<ProfessorEntity>> buscarTodos() {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarTodos());
    }

    @GetMapping("{id}")
    public ResponseEntity<ProfessorEntity> buscarTodos(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ProfessorEntity> criarProfessor(@RequestBody ProfessorEntity professor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(professor));
    }

    @PutMapping("{id}")
    public ResponseEntity<ProfessorEntity> alterarProfessor(@PathVariable Long id, @RequestBody ProfessorEntity professor) {
        return ResponseEntity.status(HttpStatus.OK).body(service.alterar(id, professor));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarProfessor(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }


}

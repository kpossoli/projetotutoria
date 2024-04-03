package br.com.projeto.tutoria.controllers;


import br.com.projeto.tutoria.entities.DisciplinaMatriculaEntity;
import br.com.projeto.tutoria.services.DisciplinaMatriculaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("disciplina-matriculas")
public class DisciplinaMatriculaController {

    private final DisciplinaMatriculaService service;

    @GetMapping
    public ResponseEntity<List<DisciplinaMatriculaEntity>> buscarTodos() {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarTodos());
    }

    @GetMapping("{id}")
    public ResponseEntity<DisciplinaMatriculaEntity> buscarTodos(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<DisciplinaMatriculaEntity> criarDisciplinaMatricula(@RequestBody DisciplinaMatriculaEntity disciplinaMatricula) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(disciplinaMatricula));
    }

    @PutMapping("{id}")
    public ResponseEntity<DisciplinaMatriculaEntity> alterarDisciplinaMatricula(@PathVariable Long id, @RequestBody DisciplinaMatriculaEntity disciplinaMatricula) {
        return ResponseEntity.status(HttpStatus.OK).body(service.alterar(id, disciplinaMatricula));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarDisciplinaMatricula(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

}

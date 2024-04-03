package br.com.projeto.tutoria.controllers;


import br.com.projeto.tutoria.entities.DisciplinaEntity;
import br.com.projeto.tutoria.services.DisciplinaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("disciplinas")
public class DisciplinasController {

    private final DisciplinaService service;

    @GetMapping
    public ResponseEntity<List<DisciplinaEntity>> buscarTodos() {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarTodos());
    }

    @GetMapping("{id}")
    public ResponseEntity<DisciplinaEntity> buscarTodos(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarPorId(id));
    }


    //TODO Tratar o erro caso a id do professor seja inválida
    @PostMapping
    public ResponseEntity<DisciplinaEntity> criarDisciplina(@RequestBody DisciplinaEntity disciplina) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(disciplina));
    }

    @PutMapping("{id}")
    public ResponseEntity<DisciplinaEntity> alterarDisciplina(@PathVariable Long id, @RequestBody DisciplinaEntity disciplina) {
        return ResponseEntity.status(HttpStatus.OK).body(service.alterar(id, disciplina));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarDisciplina(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

}

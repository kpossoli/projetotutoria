package br.com.projeto.tutoria.controllers;


import br.com.projeto.tutoria.entities.DisciplinaEntity;
import br.com.projeto.tutoria.services.DisciplinaService;
import br.com.projeto.tutoria.utils.JsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("disciplinas")
public class DisciplinasController {

    private final DisciplinaService service;

    @GetMapping
    public ResponseEntity<List<DisciplinaEntity>> buscarTodos() {
        log.info("GET /disciplinas -> Início");
        List<DisciplinaEntity> disciplinas = service.buscarTodos();
        log.info("GET /disciplinas -> Encontrados {} registros", disciplinas.size());
        log.info("GET /disciplinas -> 200 OK");
        log.debug("GET /disciplinas -> Response Body:\n{}\n", JsonUtil.objetoParaJson(disciplinas));
        return ResponseEntity.status(HttpStatus.OK).body(disciplinas);
    }


    @GetMapping("{id}")
    public ResponseEntity<DisciplinaEntity> buscarPorId(@PathVariable Long id) {
        log.info("GET /disciplinas/{} -> Início", id);
        DisciplinaEntity disciplina = service.buscarPorId(id);
        log.info("GET /disciplinas/{} -> Encontrado", id);
        log.info("GET /disciplinas/{} -> 200 OK", id);
        log.debug("GET /disciplinas/{} -> Response Body:\n{}\n", id, JsonUtil.objetoParaJson(disciplina));
        return ResponseEntity.status(HttpStatus.OK).body(disciplina);
    }

    @PostMapping
    public ResponseEntity<DisciplinaEntity> criarDisciplina(@RequestBody DisciplinaEntity disciplinaRequisicao) {
        log.info("POST /disciplinas");
        DisciplinaEntity disciplina = service.criar(disciplinaRequisicao);
        log.info("POST /disciplinas -> Cadastrado");
        log.info("POST /disciplinas -> 201 CREATED");
        log.debug("POST /disciplinas -> Response Body:\n{}\n", JsonUtil.objetoParaJson(disciplinaRequisicao));
        return ResponseEntity.status(HttpStatus.CREATED).body(disciplina);
    }


    @PutMapping("{id}")
    public ResponseEntity<DisciplinaEntity> alterarDisciplina(@PathVariable Long id, @RequestBody DisciplinaEntity disciplinaRequisicao) throws Exception {
        log.info("PUT /disciplinas/{}", id);
        DisciplinaEntity disciplina = service.alterar(id, disciplinaRequisicao);
        log.info("PUT /disciplinas/{} -> Atualizado", id);
        log.info("PUT /disciplinas/{} -> 200 OK", id);
        log.debug("PUT /disciplinas/{} -> Response Body:\n{}\n", id, JsonUtil.objetoParaJson(disciplina));
        return ResponseEntity.status(HttpStatus.OK).body(disciplina); // Retorna 200
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws Exception {
        log.info("DELETE /disciplina/{}", id);
        service.excluir(id);
        log.info("DELETE /disciplina/{} -> Excluído", id);
        log.info("DELETE /disciplina/{} -> 204 NO CONTENT", id);
        return ResponseEntity.noContent().build();
    }

}

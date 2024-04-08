package br.com.projeto.tutoria.controllers;


import br.com.projeto.tutoria.entities.AlunoEntity;
import br.com.projeto.tutoria.entities.ProfessorEntity;
import br.com.projeto.tutoria.services.ProfessorService;
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
@RequestMapping("professores")
public class ProfessorController {

    private final ProfessorService service;

    @GetMapping
    public ResponseEntity<List<ProfessorEntity>> buscarTodos() {
        log.info("GET /professores -> Início");
        List<ProfessorEntity> professores = service.buscarTodos();
        log.info("GET /professores -> Encontrados {} registros", professores.size());
        log.info("GET /professores -> 200 OK");
        log.debug("GET /professores -> Response Body:\n{}\n", JsonUtil.objetoParaJson(professores));

        return ResponseEntity.status(HttpStatus.OK).body(professores);
    }

    //TODO tratar o erro ao não colocar ID
    @GetMapping("{id}")
    public ResponseEntity<ProfessorEntity> buscarPorId(@PathVariable Long id) {
        log.info("GET /professores/{} -> Início", id);
        ProfessorEntity professor = service.buscarPorId(id);
        log.info("GET /professores/{} -> Encontrado", id);
        log.info("GET /professores/{} -> 200 OK", id);
        log.debug("GET /professores/{} -> Response Body:\n{}\n", id, JsonUtil.objetoParaJson(professor));
        return ResponseEntity.status(HttpStatus.OK).body(professor);
    }

    @PostMapping
    public ResponseEntity<ProfessorEntity> criarProfessor(@RequestBody ProfessorEntity professorRequest) {
        log.info("POST /professores -> Início");
        ProfessorEntity professor = service.criar(professorRequest);
        log.info("POST /professores -> Criado com sucesso");
        log.info("POST /professores -> 201 Created");
        log.debug("POST /professores -> Response Body:\n{}\n", JsonUtil.objetoParaJson(professorRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(professor);
    }

    //TODO tratar o erro ao não colocar ID
    @PutMapping("{id}")
    public ResponseEntity<ProfessorEntity> alterarProfessor(@PathVariable Long id, @RequestBody ProfessorEntity professorRequest) {
        log.info("PUT /professores/{} -> Início", id);
        ProfessorEntity professor = service.alterar(id, professorRequest);
        log.info("PUT /professores/{} -> Atualizado com sucesso", id);
        log.info("PUT /professores/{} -> 200 OK", id);
        log.debug("PUT /professores/{} -> Response Body:\n{}\n", id, JsonUtil.objetoParaJson(professorRequest));
        return ResponseEntity.status(HttpStatus.OK).body(professor);
    }

    //TODO tratar o erro ao não colocar ID
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarProfessor(@PathVariable Long id) {
        log.info("DELETE /professores/{}", id);
        service.excluir(id);
        log.info("DELETE /professores/{} -> Excluído", id);
        log.info("DELETE /professores/{} -> 204 No Content", id);
        return ResponseEntity.noContent().build();
    }

}

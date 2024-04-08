package br.com.projeto.tutoria.controllers;


import br.com.projeto.tutoria.entities.AlunoEntity;
import br.com.projeto.tutoria.services.AlunoService;
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
@RequestMapping("alunos")
public class AlunoController {

    private final AlunoService service;


    @GetMapping
    public ResponseEntity<List<AlunoEntity>> buscarTodos() {
        log.info("GET /alunos -> Início");

        List<AlunoEntity> alunos = service.buscarTodos();
        log.info("GET /alunos -> Encontrados {} registros", alunos.size());

        log.info("GET /alunos -> 200 OK");
        log.debug("GET /alunos -> Response Body:\n{}\n", JsonUtil.objetoParaJson(alunos));
        return ResponseEntity.status(HttpStatus.OK).body(alunos);
    }

    
    @GetMapping("{id}")
    public ResponseEntity<AlunoEntity> buscarPorId(@PathVariable Long id) {
        log.info("GET /alunos -> Início" , id );

        AlunoEntity aluno = service.buscarPorId(id);
        log.info("GET /alunos/{} -> Encontrado", id);

        log.info("GET /alunos/{} -> 200 OK", id);
        log.debug("GET /alunos/{} -> Response Body:\n{}\n", id, JsonUtil.objetoParaJson(aluno));

        return ResponseEntity.status(HttpStatus.OK).body(aluno);
    }

    @PostMapping
    public ResponseEntity<AlunoEntity> criarAluno(@RequestBody AlunoEntity alunoRequisicao) {
        log.info("POST /alunos");

        AlunoEntity aluno = service.criar(alunoRequisicao);
        log.info("POST /alunos -> Cadastrado");

        log.info("POST /alunos -> 201 CREATED");
        log.debug("POST /alunos -> Response Body:\n{}\n", JsonUtil.objetoParaJson(alunoRequisicao));
        return ResponseEntity.status(HttpStatus.CREATED).body(aluno);
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

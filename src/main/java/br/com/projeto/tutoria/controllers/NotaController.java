package br.com.projeto.tutoria.controllers;


import br.com.projeto.tutoria.dto.NotaLancamentoDTO;
import br.com.projeto.tutoria.entities.NotaEntity;
import br.com.projeto.tutoria.entities.ProfessorEntity;
import br.com.projeto.tutoria.services.NotaService;
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
@RequestMapping("notas")
public class NotaController {

    private final NotaService service;

    //Método extra
    @GetMapping
    public ResponseEntity<List<NotaEntity>> buscarTodos() {
        log.info("GET /notas -> Início");
        List<NotaEntity> notas = service.buscarTodos();
        log.info("GET /notas -> Encontrados {} registros", notas.size());
        log.info("GET /notas -> 200 OK");
        log.debug("GET /notas -> Response Body:\n{}\n", JsonUtil.objetoParaJson(notas));
        return ResponseEntity.status(HttpStatus.OK).body(notas);
    }

    //Método extra

    @GetMapping("{id}")
    public ResponseEntity<NotaEntity> buscarPorId(@PathVariable Long id) {
        log.info("GET /notas -> Início", id);
        NotaEntity notas = service.buscarPorId(id);
        log.info("GET /notas/{} -> Encontrado", id);
        log.info("GET /notas/{} -> 200 OK", id);
        log.debug("GET /notas/{} -> Response Body:\n{}\n", id, JsonUtil.objetoParaJson(notas));
        return ResponseEntity.status(HttpStatus.OK).body(notas);
    }

    @PostMapping("/lancar")
    public ResponseEntity<NotaEntity> lancarNota(@RequestBody NotaLancamentoDTO notaLancamentoDTO) {
        log.info("POST /notas/lancar");
        NotaEntity novaNota = service.lancarNota(notaLancamentoDTO);
        log.info("POST /notas/lancar -> Cadastrado");
        log.info("POST /notas/lancar -> 201 CREATED");
        log.debug("POST /notas/lancar -> Response Body:\n{}\n", JsonUtil.objetoParaJson(novaNota));
        return ResponseEntity.status(HttpStatus.CREATED).body(novaNota);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarNota(@PathVariable Long id) {
        log.info("DELETE /notas/{}", id);
        service.excluir(id);
        log.info("DELETE /notas/{} -> Excluído", id);
        log.info("DELETE /notas/{} -> 204 No Content", id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/por-matricula/{matriculaId}")
    public ResponseEntity<List<NotaEntity>> buscarNotasPorMatriculaId(@PathVariable Long matriculaId) {
        log.info("GET /notas/por-matricula/{} -> Início", matriculaId);
        List<NotaEntity> notas = service.buscarNotasPorMatriculaId(matriculaId);
        log.info("GET /notas/por-matricula/{} -> Encontrados {} registros", notas.size());
        log.info("GET /notas/por-matricula/{} -> 200 OK");
        log.debug("GET /notas/por-matricula/{} -> Response Body:\n{}\n", JsonUtil.objetoParaJson(notas));
        return notas.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(notas);
    }

}

package br.com.projeto.tutoria.controllers;


import br.com.projeto.tutoria.dto.NotaLancamentoDTO;
import br.com.projeto.tutoria.entities.NotaEntity;
import br.com.projeto.tutoria.services.NotaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("notas")
public class NotaController {

    private final NotaService service;

    @GetMapping
    public ResponseEntity<List<NotaEntity>> buscarTodos() {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarTodos());
    }

    @GetMapping("{id}")
    public ResponseEntity<NotaEntity> buscarTodos(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<NotaEntity> criarNota(@RequestBody NotaEntity nota) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(nota));
    }

    @PostMapping("/lancar")
    public ResponseEntity<NotaEntity> lancarNota(@RequestBody NotaLancamentoDTO notaLancamentoDTO) {
        NotaEntity novaNota = service.lancarNota(notaLancamentoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaNota);
    }

    @PutMapping("{id}")
    public ResponseEntity<NotaEntity> alterarNota(@PathVariable Long id, @RequestBody NotaEntity nota) {
        return ResponseEntity.status(HttpStatus.OK).body(service.alterar(id, nota));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarNota(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/por-matricula/{matriculaId}")
    public ResponseEntity<List<NotaEntity>> buscarNotasPorMatriculaId(@PathVariable Long matriculaId) {
        List<NotaEntity> notas = service.buscarNotasPorMatriculaId(matriculaId);
        return notas.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(notas);
    }


}

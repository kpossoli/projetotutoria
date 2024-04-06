package br.com.projeto.tutoria.controllers;


import br.com.projeto.tutoria.dto.AlunoMediaDTO;
import br.com.projeto.tutoria.dto.MatriculaRequestDTO;
import br.com.projeto.tutoria.entities.DisciplinaMatriculaEntity;
import br.com.projeto.tutoria.services.DisciplinaMatriculaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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

    //TODO Tratamento de erro caso a id do aluno não exista
    @GetMapping("/por-aluno/{alunoId}")
    public ResponseEntity<List<DisciplinaMatriculaEntity>> buscarPorAluno(@PathVariable Long alunoId) {
        List<DisciplinaMatriculaEntity> matriculas = service.buscarPorAlunoId(alunoId);
        return ResponseEntity.ok(matriculas);
    }

    @GetMapping("/media-disciplinas/{alunoId}")
    public ResponseEntity<AlunoMediaDTO> calcularMediaAluno(@PathVariable Long alunoId) {
        BigDecimal alunoMedia = service.calcularMediaAluno(alunoId);
        AlunoMediaDTO alunoMediaDTO = new AlunoMediaDTO(alunoMedia);
        return ResponseEntity.ok(alunoMediaDTO);
    }

    //TODO Tratamento de erro caso a id da disciplina não exista
    @GetMapping("/por-disciplina/{disciplinaId}")
    public ResponseEntity<List<DisciplinaMatriculaEntity>> buscarPorDisciplina(@PathVariable Long disciplinaId) {
        List<DisciplinaMatriculaEntity> matriculas = service.buscarPorDisciplinaId(disciplinaId);
        return ResponseEntity.ok(matriculas);
    }

    @PostMapping
    public ResponseEntity<DisciplinaMatriculaEntity> criarDisciplinaMatricula(@RequestBody DisciplinaMatriculaEntity disciplinaMatricula) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(disciplinaMatricula));
    }

    //O corpo da requisição é automaticamente mapeado para uma instância de MatriculaRequestDTO
    //@RequestBody informa ao Spring para analisar o corpo da requisição JSON e criar o DTO com esses dados.
    //Os IDs do aluno e da disciplina são extraídos do MatriculaRequestDTO e passados para o serviço
    //Após se
    @PostMapping("/matricular")
    public ResponseEntity<DisciplinaMatriculaEntity> matricularAluno(@RequestBody MatriculaRequestDTO matriculaRequestDTO) {
        DisciplinaMatriculaEntity matriculado = service.matricularAluno(matriculaRequestDTO.getAlunoId(), matriculaRequestDTO.getDisciplinaId());
        return new ResponseEntity<>(matriculado, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<DisciplinaMatriculaEntity> alterarDisciplinaMatricula(@PathVariable Long id, @RequestBody DisciplinaMatriculaEntity disciplinaMatricula) {
        return ResponseEntity.status(HttpStatus.OK).body(service.alterar(id, disciplinaMatricula));
    }

//    @DeleteMapping("{id}")
//    public ResponseEntity<Void> deletarDisciplinaMatricula(@PathVariable Long id) {
//        service.excluir(id);
//        return ResponseEntity.noContent().build();
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDisciplinaMatricula(@PathVariable Long id) throws Exception {
        //try {
        service.excluir(id);
        return ResponseEntity.noContent().build();
        // } catch (Exception e) {
        //   return ResponseEntity.badRequest().build();
        //}
    }



}

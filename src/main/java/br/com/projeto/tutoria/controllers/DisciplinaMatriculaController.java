package br.com.projeto.tutoria.controllers;


import br.com.projeto.tutoria.dto.AlunoMediaDTO;
import br.com.projeto.tutoria.dto.MatriculaRequestDTO;
import br.com.projeto.tutoria.entities.DisciplinaMatriculaEntity;
import br.com.projeto.tutoria.entities.ProfessorEntity;
import br.com.projeto.tutoria.services.DisciplinaMatriculaService;
import br.com.projeto.tutoria.utils.JsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("disciplina-matriculas")
public class DisciplinaMatriculaController {

    private final DisciplinaMatriculaService service;


    @GetMapping
    public ResponseEntity<List<DisciplinaMatriculaEntity>> buscarTodos() {
        log.info("GET /disciplina-matriculas -> Início");

        List<DisciplinaMatriculaEntity> disciplinaMatricula = service.buscarTodos();
        log.info("GET /disciplina-matriculas -> Encontrados {} registros", disciplinaMatricula.size());

        log.info("GET /disciplina-matriculas -> 200 OK");
        log.debug("GET /disciplina-matriculas-> Response Body:\n{}\n", JsonUtil.objetoParaJson(disciplinaMatricula));
        return ResponseEntity.status(HttpStatus.OK).body(disciplinaMatricula);
    }

    @GetMapping("{id}")
    public ResponseEntity<DisciplinaMatriculaEntity> buscarPorId(@PathVariable Long id) {
        log.info("GET /disciplina-matriculas/{} -> Início", id);

        DisciplinaMatriculaEntity disciplinaMatricula = service.buscarPorId(id);
        log.info("GET /disciplina-matriculas/{} -> Encontrado", id);

        log.info("GET /disciplina-matriculas/{} -> 200 OK", id);
        log.debug("GET /disciplina-matriculas/{} -> Response Body:\n{}\n", id, JsonUtil.objetoParaJson(disciplinaMatricula));
        return ResponseEntity.status(HttpStatus.OK).body(disciplinaMatricula);
    }

    //TODO Tratamento de erro caso a id do aluno não exista
    @GetMapping("/por-aluno/{alunoId}")
    public ResponseEntity<List<DisciplinaMatriculaEntity>> buscarPorAluno(@PathVariable Long alunoId) {
        log.info("GET /disciplina-matriculas/por-aluno/{} -> Início", alunoId);

        List<DisciplinaMatriculaEntity> matriculas = service.buscarPorAlunoId(alunoId);
        // DisciplinaMatriculaEntity disciplinaMatricula = facade.buscarPorId(id);
        log.info("GET /disciplina-matriculas/por-aluno/{} -> Encontrado", alunoId);

        log.info("GET /disciplina-matriculas/por-aluno/{} -> 200 OK", alunoId);
        log.debug("GET /disciplina-matriculas/por-aluno/{} -> Response Body:\n{}\n", alunoId, JsonUtil.objetoParaJson(matriculas));
        return ResponseEntity.ok(matriculas);
    }

    @GetMapping("/media-disciplinas/{alunoId}")
    public ResponseEntity<AlunoMediaDTO> calcularMediaAluno(@PathVariable Long alunoId) {
        log.info("GET /disciplina-matriculas/media-disciplinas/{} -> Início", alunoId);
        BigDecimal alunoMedia = service.calcularMediaAluno(alunoId);
        AlunoMediaDTO alunoMediaDTO = new AlunoMediaDTO(alunoMedia);
        log.info("GET /disciplina-matriculas/media-disciplinas/{} -> Encontrado", alunoId);

        log.info("GET /disciplina-matriculas/media-disciplinas/{} -> 200 OK", alunoId);
        log.debug("GET /disciplina-matriculas/media-disciplinas/{} -> Response Body:\n{}\n", alunoId, JsonUtil.objetoParaJson(alunoMediaDTO));
        return ResponseEntity.ok(alunoMediaDTO);
    }

    @GetMapping("/por-disciplina/{disciplinaId}")
    public ResponseEntity<List<DisciplinaMatriculaEntity>> buscarPorDisciplina(@PathVariable Long disciplinaId) {
        log.info("GET /disciplina-matriculas/por-disciplina/{} -> Início", disciplinaId);
        List<DisciplinaMatriculaEntity> matriculas = service.buscarPorDisciplinaId(disciplinaId);
        log.info("GET /disciplina-matriculas/por-disciplina/{} -> Encontrado", disciplinaId);
        log.info("GET /disciplina-matriculas/por-disciplina/{} -> 200 OK", disciplinaId);
        log.debug("GET /disciplina-matriculas/por-disciplina/{} -> Response Body:\n{}\n", disciplinaId, JsonUtil.objetoParaJson(matriculas));
        return ResponseEntity.ok(matriculas);
    }


//    @PostMapping
//    public ResponseEntity<DisciplinaMatriculaEntity> criarDisciplinaMatricula(@RequestBody DisciplinaMatriculaEntity disciplinaMatricula) {
//        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(disciplinaMatricula));
//    }

    //O corpo da requisição é automaticamente mapeado para uma instância de MatriculaRequestDTO
    //@RequestBody informa ao Spring para analisar o corpo da requisição JSON e criar o DTO com esses dados.
    //Os IDs do aluno e da disciplina são extraídos do MatriculaRequestDTO e passados para o serviço
    //Após se

    @PostMapping("/matricular")
    public ResponseEntity<DisciplinaMatriculaEntity> matricularAluno(@RequestBody MatriculaRequestDTO matriculaRequestDTO) {
        log.info("POST /disciplina-matricula/matricular");
        DisciplinaMatriculaEntity matriculado = service.matricularAluno(matriculaRequestDTO.getAlunoId(), matriculaRequestDTO.getDisciplinaId());
        log.info("POST /disciplina-matricula/matricular -> Cadastrado");
        log.info("POST /disciplina-matricula/matricular -> 201 CREATED");
        log.debug("POST /disciplina-matricula/matricular -> Response Body:\n{}\n", JsonUtil.objetoParaJson(matriculado));
        return new ResponseEntity<>(matriculado, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<DisciplinaMatriculaEntity> alterarDisciplinaMatricula(@PathVariable Long id, @RequestBody DisciplinaMatriculaEntity disciplinaMatricula) {
        log.info("PUT /disciplina-matriculas/{} -> Início", id);

        DisciplinaMatriculaEntity disciplina = service.alterar(id, disciplinaMatricula);


        log.info("PUT /disciplina-matriculas/{} -> Atualizado com sucesso", id);
        log.info("PUT /disciplina-matriculas/{} -> 200 OK", id);
        log.debug("PUT /disciplina-matriculas/{} -> Response Body:\n{}\n", id, JsonUtil.objetoParaJson(disciplina));
        return ResponseEntity.status(HttpStatus.OK).body(disciplina);
    }

//    @DeleteMapping("{id}")
//    public ResponseEntity<Void> deletarDisciplinaMatricula(@PathVariable Long id) {
//        service.excluir(id);
//        return ResponseEntity.noContent().build();
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDisciplinaMatricula(@PathVariable Long id) throws Exception {
        //try {
        log.info("DELETE /disciplina-matriculas/{}", id);
        service.excluir(id);
        log.info("DELETE /disciplina-matriculas/{} -> Excluído", id);
        log.info("DELETE /disciplina-matriculas/{} -> 204 No Content", id);
        return ResponseEntity.noContent().build();
        // } catch (Exception e) {
        //   return ResponseEntity.badRequest().build();
        //}
    }


}

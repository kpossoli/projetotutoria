package br.com.projeto.tutoria.services;

import br.com.projeto.tutoria.entities.DisciplinaMatriculaEntity;

import java.math.BigDecimal;
import java.util.List;

public interface DisciplinaMatriculaService {

    List<DisciplinaMatriculaEntity> buscarTodos();

    DisciplinaMatriculaEntity buscarPorId(Long id);

    DisciplinaMatriculaEntity alterar(Long id, DisciplinaMatriculaEntity entity);

    DisciplinaMatriculaEntity matricularAluno(Long alunoId, Long disciplinaId);

    List<DisciplinaMatriculaEntity> buscarPorAlunoId(Long alunoId);

    List<DisciplinaMatriculaEntity> buscarPorDisciplinaId(Long disciplinaId);

    void excluir(Long id) throws Exception;


    BigDecimal calcularMediaAluno(Long alunoId);
}
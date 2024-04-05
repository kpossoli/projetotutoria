package br.com.projeto.tutoria.services;

import br.com.projeto.tutoria.entities.DisciplinaMatriculaEntity;

import java.util.List;

public interface DisciplinaMatriculaService {

    List<DisciplinaMatriculaEntity> buscarTodos();

    DisciplinaMatriculaEntity buscarPorId(Long id);

    DisciplinaMatriculaEntity criar(DisciplinaMatriculaEntity entity);

    DisciplinaMatriculaEntity alterar(Long id, DisciplinaMatriculaEntity entity);

    DisciplinaMatriculaEntity matricularAluno(Long alunoId, Long disciplinaId);

    List<DisciplinaMatriculaEntity> buscarPorAlunoId(Long alunoId);

    List<DisciplinaMatriculaEntity> buscarPorDisciplinaId(Long disciplinaId);

    void excluir(Long id) throws Exception;


}
package br.com.projeto.tutoria.services;

import br.com.projeto.tutoria.entities.DisciplinaMatriculaEntity;

import java.util.List;

public interface DisciplinaMatriculaService {

    List<DisciplinaMatriculaEntity> buscarTodos();

    DisciplinaMatriculaEntity buscarPorId(Long id);

    DisciplinaMatriculaEntity criar(DisciplinaMatriculaEntity entity);

    DisciplinaMatriculaEntity alterar(Long id, DisciplinaMatriculaEntity entity);

    void excluir(Long id);

}

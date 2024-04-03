package br.com.projeto.tutoria.services;

import br.com.projeto.tutoria.entities.ProfessorEntity;

import java.util.List;

public interface ProfessorService {

    List<ProfessorEntity> buscarTodos();

    ProfessorEntity buscarPorId(Long id);

    ProfessorEntity criar(ProfessorEntity entity);

    ProfessorEntity alterar(Long id, ProfessorEntity entity);

    void excluir(Long id);

}

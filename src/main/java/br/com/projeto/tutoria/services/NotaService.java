package br.com.projeto.tutoria.services;

import br.com.projeto.tutoria.entities.NotaEntity;

import java.math.BigDecimal;
import java.util.List;

public interface NotaService {

    List<NotaEntity> buscarTodos();

    NotaEntity buscarPorId(Long id);

    NotaEntity criar(NotaEntity entity);

    NotaEntity alterar(Long id, NotaEntity entity);

    void excluir(Long id);

    List<NotaEntity> buscarPorMatriculaId(Long matriculaId);

    NotaEntity lancarNota(Long disciplinaMatriculaId, BigDecimal nota, BigDecimal coeficiente);
}

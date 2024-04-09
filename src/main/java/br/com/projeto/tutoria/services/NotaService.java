package br.com.projeto.tutoria.services;

import br.com.projeto.tutoria.dto.NotaLancamentoDTO;
import br.com.projeto.tutoria.entities.NotaEntity;

import java.util.List;

public interface NotaService {

    List<NotaEntity> buscarTodos();

    NotaEntity buscarPorId(Long id);

    NotaEntity criar(NotaEntity entity);

    NotaEntity alterar(Long id, NotaEntity entity);

    void excluir(Long id);

    NotaEntity lancarNota(NotaLancamentoDTO notaLancamentoDTO);

    List<NotaEntity> buscarNotasPorMatriculaId(Long matriculaId);
}

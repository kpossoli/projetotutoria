package br.com.projeto.tutoria.repositories;

import br.com.projeto.tutoria.entities.NotaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface NotaRepository extends JpaRepository<NotaEntity, Long> {
    boolean existsByDisciplinaMatriculaId(Long disciplinaMatriculaId);
    List<NotaEntity> findByDisciplinaMatriculaId(Long disciplinaMatriculaId);

}

package br.com.projeto.tutoria.repositories;

import br.com.projeto.tutoria.entities.NotaEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NotaRepository extends JpaRepository<NotaEntity, Long> {
    boolean existsByDisciplinaMatriculaId(Long disciplinaMatriculaId);
}

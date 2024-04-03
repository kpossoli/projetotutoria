package br.com.projeto.tutoria.repositories;

import br.com.projeto.tutoria.entities.DisciplinaMatriculaEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DisciplinaMatriculaRepository extends JpaRepository<DisciplinaMatriculaEntity, Long> {
}

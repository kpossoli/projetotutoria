package br.com.projeto.tutoria.repositories;

import br.com.projeto.tutoria.entities.ProfessorEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProfessorRepository extends JpaRepository<ProfessorEntity, Long> {
}

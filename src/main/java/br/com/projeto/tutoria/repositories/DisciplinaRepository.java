package br.com.projeto.tutoria.repositories;

import br.com.projeto.tutoria.entities.DisciplinaEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DisciplinaRepository extends JpaRepository<DisciplinaEntity, Long> {
}

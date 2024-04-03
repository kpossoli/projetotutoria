package br.com.projeto.tutoria.repositories;

import br.com.projeto.tutoria.entities.AlunoEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AlunoRepository extends JpaRepository<AlunoEntity, Long> {
}


package br.com.projeto.tutoria.repositories;

import br.com.projeto.tutoria.entities.AlunoEntity;
import br.com.projeto.tutoria.entities.DisciplinaEntity;
import br.com.projeto.tutoria.entities.DisciplinaMatriculaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface DisciplinaMatriculaRepository extends JpaRepository<DisciplinaMatriculaEntity, Long> {

    List<DisciplinaMatriculaEntity> findByAlunoId(Long alunoId);
    List<DisciplinaMatriculaEntity> findByDisciplinaId(Long disciplinaId);
    boolean existsByAlunoAndDisciplina(AlunoEntity aluno, DisciplinaEntity disciplina);

}

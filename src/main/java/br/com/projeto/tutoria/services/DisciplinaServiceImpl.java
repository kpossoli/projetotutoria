package br.com.projeto.tutoria.services;

import br.com.projeto.tutoria.entities.DisciplinaEntity;
import br.com.projeto.tutoria.exceptions.DisciplinaByIdNotFoundException;
import br.com.projeto.tutoria.exceptions.NotFoundException;
import br.com.projeto.tutoria.exceptions.ProfessorByIdNotFoundException;
import br.com.projeto.tutoria.repositories.DisciplinaRepository;
import br.com.projeto.tutoria.repositories.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplinaServiceImpl implements DisciplinaService {

    private final DisciplinaRepository disciplinaRepository;
    private final ProfessorRepository professorRepository;

    public DisciplinaServiceImpl(DisciplinaRepository disciplinaRepository, ProfessorRepository professorRepository) {
        this.disciplinaRepository = disciplinaRepository;
        this.professorRepository = professorRepository;
    }

    @Override
    public List<DisciplinaEntity> buscarTodos() {
        return disciplinaRepository.findAll();
    }

    @Override
    public DisciplinaEntity buscarPorId(Long id) {
        return disciplinaRepository.findById(id)
                .orElseThrow(() -> new DisciplinaByIdNotFoundException(id));
    }

//    @Override
//    public DisciplinaEntity criar(DisciplinaEntity entity) {
//        if (!professorRepository.existsById(entity.getProfessor().getId())) {
//            throw new IllegalArgumentException("O id do professor não foi encontrado.");
//        }
//        return disciplinaRepository.save(entity);
//    }

    @Override
    public DisciplinaEntity criar(DisciplinaEntity entity) {
        Long professorId = entity.getProfessor().getId();

        if (!professorRepository.existsById(professorId)) {
            throw new ProfessorByIdNotFoundException(professorId);
        }

        return disciplinaRepository.save(entity);
    }

    @Override
    public DisciplinaEntity alterar(Long id, DisciplinaEntity entity) {
        DisciplinaEntity disciplina = disciplinaRepository.findById(id)
                .orElseThrow(() -> new DisciplinaByIdNotFoundException(id));

        Long professorId = entity.getProfessor().getId();
        if (!professorRepository.existsById(professorId)) {
            throw new ProfessorByIdNotFoundException(professorId);
        }

        disciplina.setNome(entity.getNome());
        disciplina.setProfessor(entity.getProfessor());

        return disciplinaRepository.save(disciplina);
    }


    @Override
    public void excluir(Long id) {
        DisciplinaEntity entity = buscarPorId(id);
        disciplinaRepository.delete(entity);
    }
}

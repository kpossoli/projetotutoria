package br.com.projeto.tutoria.services;

import br.com.projeto.tutoria.entities.ProfessorEntity;
import br.com.projeto.tutoria.exceptions.NotFoundException;
import br.com.projeto.tutoria.repositories.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorServiceImpl implements ProfessorService {

    private final ProfessorRepository professorRepository;

    public ProfessorServiceImpl(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @Override
    public List<ProfessorEntity> buscarTodos() {
        return professorRepository.findAll();
    }

    @Override
    public ProfessorEntity buscarPorId(Long id) {
        return professorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Professor não encontrado com id: " + id));
    }

    @Override
    public ProfessorEntity criar(ProfessorEntity entity) {
        return professorRepository.save(entity);
    }

    @Override
    public ProfessorEntity alterar(Long id, ProfessorEntity entity) {
        buscarPorId(id);
        entity.setId(id);
        return professorRepository.save(entity);
    }

    @Override
    public void excluir(Long id) {
        ProfessorEntity entity = buscarPorId(id);
        professorRepository.delete(entity);
    }
}

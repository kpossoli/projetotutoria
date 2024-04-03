package br.com.projeto.tutoria.services;

import br.com.projeto.tutoria.entities.DisciplinaMatriculaEntity;
import br.com.projeto.tutoria.exceptions.NotFoundException;
import br.com.projeto.tutoria.repositories.DisciplinaMatriculaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplinaMatriculaServiceImpl implements DisciplinaMatriculaService {

    private final DisciplinaMatriculaRepository repository;

    public DisciplinaMatriculaServiceImpl(DisciplinaMatriculaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<DisciplinaMatriculaEntity> buscarTodos() {
        return repository.findAll();
    }

    @Override
    public DisciplinaMatriculaEntity buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("DisciplinaMatricula não encontrada com id: " + id));
    }

    @Override
    public DisciplinaMatriculaEntity criar(DisciplinaMatriculaEntity entity) {
        return repository.save(entity);
    }

    @Override
    public DisciplinaMatriculaEntity alterar(Long id, DisciplinaMatriculaEntity entity) {
        buscarPorId(id);
        entity.setId(id);
        return repository.save(entity);
    }

    @Override
    public void excluir(Long id) {
        DisciplinaMatriculaEntity entity = buscarPorId(id);
        repository.delete(entity);
    }
}

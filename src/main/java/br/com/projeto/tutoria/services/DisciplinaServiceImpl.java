package br.com.projeto.tutoria.services;

import br.com.projeto.tutoria.entities.DisciplinaEntity;
import br.com.projeto.tutoria.exceptions.NotFoundException;
import br.com.projeto.tutoria.repositories.DisciplinaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplinaServiceImpl implements DisciplinaService {

    private final DisciplinaRepository repository;

    public DisciplinaServiceImpl(DisciplinaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<DisciplinaEntity> buscarTodos() {
        return repository.findAll();
    }

    @Override
    public DisciplinaEntity buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Disciplina não encontrada com id: " + id));
    }

    @Override
    public DisciplinaEntity criar(DisciplinaEntity entity) {
        return repository.save(entity);
    }

    @Override
    public DisciplinaEntity alterar(Long id, DisciplinaEntity entity) {
        buscarPorId(id);
        entity.setId(id);
        return repository.save(entity);
    }

    @Override
    public void excluir(Long id) {
        DisciplinaEntity entity = buscarPorId(id);
        repository.delete(entity);
    }
}

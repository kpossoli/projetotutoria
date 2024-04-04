package br.com.projeto.tutoria.services;

import br.com.projeto.tutoria.entities.NotaEntity;
import br.com.projeto.tutoria.exceptions.NotFoundException;
import br.com.projeto.tutoria.repositories.NotaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotaServiceImpl implements NotaService {

    private final NotaRepository repository;
   // private final DisciplinaMatriculaServiceImpl disciplinaMatriculaServiceImpl;
    private final DisciplinaMatriculaService disciplinaMatriculaService;

    public NotaServiceImpl(NotaRepository repository, DisciplinaMatriculaService disciplinaMatriculaService) {
        this.repository = repository;
        this.disciplinaMatriculaService = disciplinaMatriculaService;
    }

    @Override
    public List<NotaEntity> buscarTodos() {
        return repository.findAll();
    }

    @Override
    public NotaEntity buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nota não encontrada com id: " + id));
    }

    @Override
    public NotaEntity criar(NotaEntity entity) {
        return repository.save(entity);
    }

    @Override
    public NotaEntity alterar(Long id, NotaEntity entity) {
        buscarPorId(id);
        entity.setId(id);
        return repository.save(entity);
    }

    @Override
    public void excluir(Long id) {
        NotaEntity entity = buscarPorId(id);
        repository.delete(entity);
    }

    @Override
    public List<NotaEntity> buscarPorMatriculaId(Long matriculaId) {
        disciplinaMatriculaService.buscarPorId(matriculaId);
        return repository.findByDisciplinaMatriculaId(matriculaId);
    }
}

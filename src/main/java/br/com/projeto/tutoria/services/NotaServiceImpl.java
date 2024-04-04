package br.com.projeto.tutoria.services;

import br.com.projeto.tutoria.entities.AlunoEntity;
import br.com.projeto.tutoria.entities.DisciplinaMatriculaEntity;
import br.com.projeto.tutoria.entities.NotaEntity;
import br.com.projeto.tutoria.exceptions.NotFoundException;
import br.com.projeto.tutoria.repositories.DisciplinaMatriculaRepository;
import br.com.projeto.tutoria.repositories.NotaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class NotaServiceImpl implements NotaService {

    private final NotaRepository repository;
   // private final DisciplinaMatriculaServiceImpl disciplinaMatriculaServiceImpl;
    private final DisciplinaMatriculaService disciplinaMatriculaService;
    private final DisciplinaMatriculaRepository disciplinaMatriculaRepository;

    public NotaServiceImpl(NotaRepository repository, DisciplinaMatriculaService disciplinaMatriculaService, DisciplinaMatriculaRepository disciplinaMatriculaRepository) {
        this.repository = repository;
        this.disciplinaMatriculaService = disciplinaMatriculaService;
        this.disciplinaMatriculaRepository = disciplinaMatriculaRepository;
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

    @Override
    public NotaEntity lancarNota(Long disciplinaMatriculaId, BigDecimal nota, BigDecimal coeficiente) {
        DisciplinaMatriculaEntity matricula = disciplinaMatriculaRepository.findById(disciplinaMatriculaId)
                .orElseThrow(() -> new NotFoundException("Matricula não encontrada com id: " + disciplinaMatriculaId));

        NotaEntity novaNota = new NotaEntity();
        novaNota.setDisciplinaMatricula(matricula);
        novaNota.setNota(nota);
        novaNota.setCoeficiente(coeficiente);


        return repository.save(novaNota);
    }
}

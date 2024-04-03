package br.com.projeto.tutoria.services;

import br.com.projeto.tutoria.entities.AlunoEntity;
import br.com.projeto.tutoria.exceptions.NotFoundException;
import br.com.projeto.tutoria.repositories.AlunoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoServiceImpl implements AlunoService {

    private final AlunoRepository repository;

    public AlunoServiceImpl(AlunoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<AlunoEntity> buscarTodos() {
        return repository.findAll();
    }

    @Override
    public AlunoEntity buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        "Aluno não encontrado com id: " + id
                ));
    }

    @Override
    public AlunoEntity criar(AlunoEntity entity) {
        entity.setId(null); // Garante que um novo ID será gerado.
        return repository.save(entity);
    }

    @Override
    public AlunoEntity alterar(Long id, AlunoEntity entity) {
        buscarPorId(id); // Verifica a existência do Aluno.
        entity.setId(id); // Assegura que a alteração será no Aluno correto.
        return repository.save(entity);
    }

    @Override
    public void excluir(Long id) {
        AlunoEntity entity = buscarPorId(id); // Verifica se o Aluno existe antes de excluir.
        repository.delete(entity);
    }
}
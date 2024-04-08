package br.com.projeto.tutoria.services;

import br.com.projeto.tutoria.entities.AlunoEntity;
import br.com.projeto.tutoria.entities.DisciplinaEntity;
import br.com.projeto.tutoria.entities.DisciplinaMatriculaEntity;
import br.com.projeto.tutoria.exceptions.*;
import br.com.projeto.tutoria.repositories.AlunoRepository;
import br.com.projeto.tutoria.repositories.DisciplinaMatriculaRepository;
import br.com.projeto.tutoria.repositories.DisciplinaRepository;
import br.com.projeto.tutoria.repositories.NotaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class DisciplinaMatriculaServiceImpl implements DisciplinaMatriculaService {

    private final DisciplinaMatriculaRepository disciplinaMatriculaRepository;
    private final AlunoRepository alunoRepository;
    private final DisciplinaRepository disciplinaRepository;

    private final NotaRepository notaRepository;

    public DisciplinaMatriculaServiceImpl(DisciplinaMatriculaRepository disciplinaMatriculaRepository, AlunoRepository alunoRepository, DisciplinaRepository disciplinaRepository, NotaRepository notaRepository) {
        this.disciplinaMatriculaRepository = disciplinaMatriculaRepository;
        this.alunoRepository = alunoRepository;
        this.disciplinaRepository = disciplinaRepository;
        this.notaRepository = notaRepository;
    }

    @Override
    public List<DisciplinaMatriculaEntity> buscarTodos() {
        return disciplinaMatriculaRepository.findAll();
    }

    @Override
    public DisciplinaMatriculaEntity buscarPorId(Long id) {
        return disciplinaMatriculaRepository.findById(id)
                .orElseThrow(() -> new DisciplinaMatriculaByIdNotFoundException(id));
    }

    @Override
    public DisciplinaMatriculaEntity alterar(Long id, DisciplinaMatriculaEntity entity) {
        buscarPorId(id);
        entity.setId(id);
        return disciplinaMatriculaRepository.save(entity);
    }


    @Override
    public DisciplinaMatriculaEntity matricularAluno(Long alunoId, Long disciplinaId) {
        AlunoEntity aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new AlunoByIdNotFoundException(alunoId)); // refatorado
        DisciplinaEntity disciplina = disciplinaRepository.findById(disciplinaId)
                .orElseThrow(() -> new DisciplinaByIdNotFoundException(disciplinaId)); // refatorado

        DisciplinaMatriculaEntity novaMatricula = new DisciplinaMatriculaEntity();
        novaMatricula.setAluno(aluno);
        novaMatricula.setDisciplina(disciplina);

        return disciplinaMatriculaRepository.save(novaMatricula);
    }

    @Override
    public List<DisciplinaMatriculaEntity> buscarPorAlunoId(Long alunoId) {
        return disciplinaMatriculaRepository.findByAlunoId(alunoId);
    }

    @Override
    public List<DisciplinaMatriculaEntity> buscarPorDisciplinaId(Long disciplinaId) {
        DisciplinaEntity disciplina = disciplinaRepository.findById(disciplinaId)
                .orElseThrow(() -> new NotFoundException("Disciplina não encontrada com id: " + disciplinaId));
        return disciplinaMatriculaRepository.findByDisciplinaId(disciplinaId);
    }

    @Override
    public void excluir(Long id) {

        if (notaRepository.existsByDisciplinaMatriculaId(id)) {
            throw new OperacaoNaoPermitidaException(id);
        } else {
            DisciplinaMatriculaEntity entity = disciplinaMatriculaRepository.findById(id)
                    .orElseThrow(() -> new DisciplinaMatriculaByIdNotFoundException(id));
            disciplinaMatriculaRepository.delete(entity);
        }
    }

    @Override
    public BigDecimal calcularMediaAluno(Long alunoId) {
        AlunoEntity aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new AlunoByIdNotFoundException(alunoId));
        List<DisciplinaMatriculaEntity> alunoMatriculas = disciplinaMatriculaRepository.findByAlunoId(alunoId);

        BigDecimal somaMediaDisciplinas = BigDecimal.ZERO;
        BigDecimal contador = BigDecimal.ZERO;

        for (DisciplinaMatriculaEntity matricula : alunoMatriculas) {
            BigDecimal mediaDisciplinas = matricula.getMediaFinal();
            somaMediaDisciplinas = somaMediaDisciplinas.add(mediaDisciplinas);
            contador = contador.add(BigDecimal.ONE);
        }
        if (contador.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO.setScale(2);
        }

        return somaMediaDisciplinas.divide(contador, 2, RoundingMode.HALF_UP);

    }

}
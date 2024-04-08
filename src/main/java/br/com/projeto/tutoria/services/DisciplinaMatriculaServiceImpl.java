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

    @Override // GET disciplina-matriculas OK
    public List<DisciplinaMatriculaEntity> buscarTodos() {
        return disciplinaMatriculaRepository.findAll();
    }

    @Override // GET disciplina-matriculas/:id OK
    public DisciplinaMatriculaEntity buscarPorId(Long id) {
        return disciplinaMatriculaRepository.findById(id)
                .orElseThrow(() -> new DisciplinaMatriculaByIdNotFoundException(id));
    }

    @Override // PUT disciplina-matriculas/:id OK
    public DisciplinaMatriculaEntity alterar(Long id, DisciplinaMatriculaEntity entity) {
        buscarPorId(id);
        entity.setId(id);
        return disciplinaMatriculaRepository.save(entity);
    }

    @Override
    public DisciplinaMatriculaEntity matricularAluno(Long alunoId, Long disciplinaId) {
        AlunoEntity aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new AlunoByIdNotFoundException(alunoId));
        DisciplinaEntity disciplina = disciplinaRepository.findById(disciplinaId)
                .orElseThrow(() -> new DisciplinaByIdNotFoundException(disciplinaId));

        // Verifica se já existe uma matrícula para este aluno nesta disciplina
        if (disciplinaMatriculaRepository.existsByAlunoAndDisciplina(aluno, disciplina)) {
            throw new MatriculaDuplicadaException(alunoId, disciplinaId);
        }

        DisciplinaMatriculaEntity novaMatricula = new DisciplinaMatriculaEntity();
        novaMatricula.setAluno(aluno);
        novaMatricula.setDisciplina(disciplina);

        return disciplinaMatriculaRepository.save(novaMatricula);
    }

    @Override // GET disciplina-matriculas/por-aluno/:id OK
    public List<DisciplinaMatriculaEntity> buscarPorAlunoId(Long alunoId) {
        if (!alunoRepository.existsById(alunoId)) {
            throw new AlunoByIdNotFoundException(alunoId);
        }
        List<DisciplinaMatriculaEntity> matriculas = disciplinaMatriculaRepository.findByAlunoId(alunoId);
        return matriculas;
    }


    @Override // disciplina-matriculas/por-disciplina/:id OK // corregir id vazio
    public List<DisciplinaMatriculaEntity> buscarPorDisciplinaId(Long disciplinaId) {
        DisciplinaEntity disciplina = disciplinaRepository.findById(disciplinaId)
                .orElseThrow(() -> new NotFoundException("Disciplina não encontrada com id: " + disciplinaId));
        return disciplinaMatriculaRepository.findByDisciplinaId(disciplinaId);
    }

    @Override // disciplina-matriculas/:id OK // corregir id vazio
    public void excluir(Long id) {

        if (notaRepository.existsByDisciplinaMatriculaId(id)) {
            throw new OperacaoNaoPermitidaException(id);
        } else {
            DisciplinaMatriculaEntity entity = disciplinaMatriculaRepository.findById(id)
                    .orElseThrow(() -> new DisciplinaMatriculaByIdNotFoundException(id));
            disciplinaMatriculaRepository.delete(entity);
        }
    }
    @Override // disciplina-matriculas/media-disciplinas/:id OK
    public BigDecimal calcularMediaAluno(Long alunoId) {
        List<DisciplinaMatriculaEntity> matriculas = disciplinaMatriculaRepository.findByAlunoId(alunoId);
        BigDecimal somaMediaDisciplinas = BigDecimal.ZERO;
        int contadorDisciplinasAvaliadas = 0;

        for (DisciplinaMatriculaEntity matricula : matriculas) {
            BigDecimal mediaDisciplina = matricula.getMediaFinal();
            // Verifica se a disciplina foi avaliada (média final > 0)
            if (mediaDisciplina.compareTo(BigDecimal.ZERO) > 0) {
                somaMediaDisciplinas = somaMediaDisciplinas.add(mediaDisciplina);
                contadorDisciplinasAvaliadas++;
            }
        }
        // Evita divisão por zero se o aluno não foi avaliado em nenhuma disciplina
        if (contadorDisciplinasAvaliadas == 0) {
            return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        }

        return somaMediaDisciplinas.divide(BigDecimal.valueOf(contadorDisciplinasAvaliadas), 2, RoundingMode.HALF_UP);
    }

}
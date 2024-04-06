package br.com.projeto.tutoria.services;

import br.com.projeto.tutoria.entities.AlunoEntity;
import br.com.projeto.tutoria.entities.DisciplinaEntity;
import br.com.projeto.tutoria.entities.DisciplinaMatriculaEntity;
import br.com.projeto.tutoria.exceptions.NotFoundException;
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

    public DisciplinaMatriculaServiceImpl(DisciplinaMatriculaRepository repository, AlunoRepository alunoRepository, DisciplinaRepository disciplinaRepository, NotaRepository notaRepository) {
        this.disciplinaMatriculaRepository = repository;
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
                .orElseThrow(() -> new NotFoundException("DisciplinaMatricula não encontrada com id: " + id));
    }

    @Override
    public DisciplinaMatriculaEntity criar(DisciplinaMatriculaEntity entity) {
        return disciplinaMatriculaRepository.save(entity);
    }

    @Override
    public DisciplinaMatriculaEntity alterar(Long id, DisciplinaMatriculaEntity entity) {
        buscarPorId(id);
        entity.setId(id);
        return disciplinaMatriculaRepository.save(entity);
    }

    //O serviço usa os IDs para buscar as entidades Aluno e Disciplina correspondentes no banco de dados
    //Se ambos forem encontrados, uma nova entidade DisciplinaMatriculaEntity é criada e configurada
    //com as entidades AlunoEntity e DisciplinaEntity
    @Override
    public DisciplinaMatriculaEntity matricularAluno(Long alunoId, Long disciplinaId) {
        AlunoEntity aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new NotFoundException("Aluno não encontrado com id: " + alunoId));
        DisciplinaEntity disciplina = disciplinaRepository.findById(disciplinaId)
                .orElseThrow(() -> new NotFoundException("Disciplina não encontrada com id: " + disciplinaId));

        DisciplinaMatriculaEntity novaMatricula = new DisciplinaMatriculaEntity();
        novaMatricula.setAluno(aluno);
        novaMatricula.setDisciplina(disciplina);
        // Defina valores padrão para outros campos aqui

        return disciplinaMatriculaRepository.save(novaMatricula);
    }

    @Override
    public List<DisciplinaMatriculaEntity> buscarPorAlunoId(Long alunoId) {
        return disciplinaMatriculaRepository.findByAlunoId(alunoId);
    }

    @Override
    public List<DisciplinaMatriculaEntity> buscarPorDisciplinaId(Long disciplinaId) {
        return disciplinaMatriculaRepository.findByDisciplinaId(disciplinaId);
    }

//    @Override
//    public void excluir(Long id) {
//        DisciplinaMatriculaEntity entity = buscarPorId(id);
//        repository.delete(entity);
//    }

    @Override
    public void excluir(Long id) throws Exception {
        // Verifica se existem notas lançadas para a matrícula
        if (notaRepository.existsByDisciplinaMatriculaId(id)) {
            throw new Exception("Existem notas lançadas para esta matrícula, não é possível excluir.");
        } else {
            DisciplinaMatriculaEntity entity = disciplinaMatriculaRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Matrícula não encontrada com id: " + id));
            disciplinaMatriculaRepository.delete(entity);
        }
    }

    @Override
    public BigDecimal calcularMediaAluno(Long alunoId) {
        AlunoEntity aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new NotFoundException("Aluno não encontrado com id: " + alunoId));
        List<DisciplinaMatriculaEntity> alunoMatriculas = disciplinaMatriculaRepository.findByAlunoId(alunoId);

        BigDecimal somaMediaDisciplinas = BigDecimal.ZERO;
        BigDecimal contador =BigDecimal.ZERO;

        for (DisciplinaMatriculaEntity matricula : alunoMatriculas) {
            BigDecimal mediaDisciplinas = matricula.getMediaFinal();
            somaMediaDisciplinas = somaMediaDisciplinas.add(mediaDisciplinas) ;
            contador = contador.add(BigDecimal.ONE);
        }
        if(contador.compareTo(BigDecimal.ZERO) == 0){
            return BigDecimal.ZERO;
        }

        return somaMediaDisciplinas.divide(contador, 2, RoundingMode.HALF_UP);

    }


}
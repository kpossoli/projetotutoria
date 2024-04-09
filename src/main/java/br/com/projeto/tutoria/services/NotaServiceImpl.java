package br.com.projeto.tutoria.services;

import br.com.projeto.tutoria.dto.NotaLancamentoDTO;
import br.com.projeto.tutoria.entities.DisciplinaMatriculaEntity;
import br.com.projeto.tutoria.entities.NotaEntity;
import br.com.projeto.tutoria.exceptions.NotFoundException;
import br.com.projeto.tutoria.repositories.DisciplinaMatriculaRepository;
import br.com.projeto.tutoria.repositories.NotaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class NotaServiceImpl implements NotaService {

    private final NotaRepository notaRepository;
    private DisciplinaMatriculaRepository disciplinaMatriculaRepository;

    public NotaServiceImpl(NotaRepository notaRepository, DisciplinaMatriculaRepository disciplinaMatriculaRepository) {
        this.notaRepository = notaRepository;
        this.disciplinaMatriculaRepository = disciplinaMatriculaRepository;
    }

    @Override
    public List<NotaEntity> buscarTodos() {
        return notaRepository.findAll();
    }

    @Override
    public NotaEntity buscarPorId(Long id) {
        return notaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nota não encontrada com id: " + id));
    }

    @Override
    public NotaEntity criar(NotaEntity entity) {
        return notaRepository.save(entity);
    }

    @Override
    public NotaEntity alterar(Long id, NotaEntity entity) {
        buscarPorId(id);
        entity.setId(id);
        return notaRepository.save(entity);
    }

    @Override
    public void excluir(Long id) {
        NotaEntity nota = buscarPorId(id); // Busca a nota para obter o ID da matrícula
        Long matriculaId = nota.getDisciplinaMatricula().getId(); // Obtem o ID da matrícula
        notaRepository.delete(nota); // Exclui a nota
        atualizarMediaFinal(matriculaId); // Recalcula a média final da matrícula
    }


    public NotaEntity lancarNota(NotaLancamentoDTO notaLancamentoDTO) {
        DisciplinaMatriculaEntity matricula = disciplinaMatriculaRepository.findById(notaLancamentoDTO.getDisciplinaMatriculaId())
                .orElseThrow(() -> new NotFoundException("Matrícula não encontrada com id: " + notaLancamentoDTO.getDisciplinaMatriculaId()));

        NotaEntity nota = new NotaEntity();
        nota.setDisciplinaMatricula(matricula);
        nota.setNota(notaLancamentoDTO.getNota());
        nota.setCoeficiente(notaLancamentoDTO.getCoeficiente());
        nota.setProfessor(matricula.getDisciplina().getProfessor());
        NotaEntity notaSalva = notaRepository.save(nota);

        atualizarMediaFinal(matricula.getId());

        return notaSalva;
    }

    @Override
    public List<NotaEntity> buscarNotasPorMatriculaId(Long matriculaId) {
        return notaRepository.findByDisciplinaMatriculaId(matriculaId);
    }


    private void atualizarMediaFinal(Long matriculaId) {
        List<NotaEntity> notas = notaRepository.findByDisciplinaMatriculaId(matriculaId);
        if (notas.isEmpty()) {
            setMediaFinal(matriculaId, BigDecimal.ZERO);
        } else {
            BigDecimal somaPesos = BigDecimal.ZERO;
            BigDecimal somaNotasPonderadas = BigDecimal.ZERO;

            // Iterar sobre cada nota para calcular a soma dos pesos e a soma ponderada das notas
            for (NotaEntity nota : notas) {
                BigDecimal coeficiente = nota.getCoeficiente();
                BigDecimal notaPonderada = nota.getNota().multiply(coeficiente);

                somaPesos = somaPesos.add(coeficiente);
                somaNotasPonderadas = somaNotasPonderadas.add(notaPonderada);
            }

            // Calcular a média final
            BigDecimal mediaFinal = somaNotasPonderadas.divide(somaPesos, 2, RoundingMode.HALF_UP);
            setMediaFinal(matriculaId, mediaFinal);
        }
    }

    private void setMediaFinal(Long matriculaId, BigDecimal mediaFinal) {
        DisciplinaMatriculaEntity matricula = disciplinaMatriculaRepository.findById(matriculaId)
                .orElseThrow(() -> new NotFoundException("Matrícula não encontrada com id: " + matriculaId));
        matricula.setMediaFinal(mediaFinal);
        disciplinaMatriculaRepository.save(matricula);
    }
}

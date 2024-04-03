package br.com.projeto.tutoria.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "disciplina_matriculas")
public class DisciplinaMatriculaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "aluno_id", nullable = false)
    private AlunoEntity aluno;

    @ManyToOne
    @JoinColumn(name = "disciplina_id", nullable = false)
    private DisciplinaEntity disciplina;

    @Column(nullable = false)
    private LocalDate dataMatricula = LocalDate.now();

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal mediaFinal = BigDecimal.ZERO.setScale(2);
}
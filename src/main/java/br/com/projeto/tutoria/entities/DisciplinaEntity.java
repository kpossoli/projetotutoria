package br.com.projeto.tutoria.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "disciplinas")
public class DisciplinaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150) // está bigint na tabela ¿?
    private String nome;

    @ManyToOne
    @JoinColumn(name = "professor_id", nullable = false)
    private ProfessorEntity professor;
}


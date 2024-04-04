package br.com.projeto.tutoria.dto;

import lombok.Builder;
import lombok.Data;

// Usado para capturar e transferir os dados necessários para matricular um aluno
// Ajuda a separar os dados específicos que você quer enviar/receber da lógica
// de negócios e do modelo de domínio da aplicação.
@Data
@Builder
public class MatriculaRequestDTO {
    private Long alunoId;
    private Long disciplinaId;

}

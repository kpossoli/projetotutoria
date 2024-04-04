package br.com.projeto.tutoria.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

// Usado para capturar e transferir os dados necessários para lançar uma nota
// Ajuda a separar os dados específicos que você quer enviar/receber da lógica
// de negócios e do modelo de domínio da aplicação.
@Data
@Builder
public class NotaRequestDTO {
    private Long disciplinaMatriculaId;
    private BigDecimal nota;
    private BigDecimal coeficiente;
}

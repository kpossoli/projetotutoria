package br.com.projeto.tutoria.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class NotaRequestDTO {
    private Long disciplinaMatriculaId;
    private BigDecimal nota;
    private BigDecimal coeficiente;
}

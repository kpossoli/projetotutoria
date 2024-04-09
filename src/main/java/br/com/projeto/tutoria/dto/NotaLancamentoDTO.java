package br.com.projeto.tutoria.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class NotaLancamentoDTO {
    private Long disciplinaMatriculaId;
    private BigDecimal nota;
    private BigDecimal coeficiente;
}

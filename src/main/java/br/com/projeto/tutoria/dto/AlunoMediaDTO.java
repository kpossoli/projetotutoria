package br.com.projeto.tutoria.dto;


import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class AlunoMediaDTO {

    private BigDecimal mediaGeralAluno;

    public AlunoMediaDTO(BigDecimal mediaGeralAluno) {
        this.mediaGeralAluno = mediaGeralAluno;
    }

}

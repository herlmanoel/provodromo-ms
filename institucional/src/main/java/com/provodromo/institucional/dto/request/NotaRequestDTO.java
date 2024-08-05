package com.provodromo.institucional.dto.request;


import com.provodromo.institucional.domain.Aluno;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotaRequestDTO {
        private Long turmaId;
        private Long avaliacaoId;
        private Long alunoId;
        private Double valor;
}

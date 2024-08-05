package com.provodromo.institucional.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotaCompleteResponseDTO {
        private Long id;
        private Double valor;
        private Long avaliacao;
        private TurmaResponseDTO turma;
        private AlunoResponseDTO aluno;
}

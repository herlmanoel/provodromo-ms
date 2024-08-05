package com.provodromo.institucional.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlunoTurmasResponseDTO {
    private AlunoResponseDTO alunoDTO;
    private List<TurmaResponseDTO> notaDTOS;
}

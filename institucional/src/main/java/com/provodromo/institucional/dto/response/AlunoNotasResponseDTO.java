package com.provodromo.institucional.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlunoNotasResponseDTO {
    private AlunoResponseDTO alunoDTO;
    private List<NotaResponseDTO> notaDTOS;
}

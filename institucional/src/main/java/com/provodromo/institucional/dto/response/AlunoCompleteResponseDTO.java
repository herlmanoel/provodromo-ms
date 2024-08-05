package com.provodromo.institucional.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlunoCompleteResponseDTO {
    private Long id;
    private Long usuarioId;
    private String nome;
    private String email;
    private List<NotaResponseDTO> notaDTOS;
    private List<TurmaResponseDTO> turmas;
}

package com.provodromo.provodromo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TurmaResponseDTO {
    private String nome;
    private Long professorId;
}

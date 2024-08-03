package com.provodromo.provodromo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TurmaRequestDTO {
    private String nome;
    private Long professorId;
}

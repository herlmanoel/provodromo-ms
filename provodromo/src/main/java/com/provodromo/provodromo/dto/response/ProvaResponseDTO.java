package com.provodromo.provodromo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProvaResponseDTO {
    private Long id;
    private String titulo;
    private Long turmaId;
    private Long nota;
    private Set<Long> questoesIds;
}

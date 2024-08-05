package com.provodromo.institucional.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TurmaRequestDTO {
    private Long professorId;
    private String nome;
}

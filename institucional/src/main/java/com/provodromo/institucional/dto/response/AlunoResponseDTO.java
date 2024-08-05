package com.provodromo.institucional.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.function.LongFunction;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AlunoResponseDTO {
    private Long id;
    private Long usuarioId;
    private String nome;
    private String email;
}

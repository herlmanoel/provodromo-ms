package com.provodromo.institucional.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorResponseDTO {
        private Long id;
        private Long usuarioId;
        private String nome;
        private String email;
}
package com.provodromo.institucional.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotaResponseDTO {
        private Long id;
        private Double valor;
        private Long avaliacao;
}

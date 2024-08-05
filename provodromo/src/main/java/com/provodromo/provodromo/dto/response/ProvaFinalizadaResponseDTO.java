package com.provodromo.provodromo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProvaFinalizadaResponseDTO {
    private Long id;
    private Long provaId;
    private Long usuarioId;
    private Long nota;
}

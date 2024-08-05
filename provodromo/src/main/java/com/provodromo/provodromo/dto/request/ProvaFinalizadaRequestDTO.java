package com.provodromo.provodromo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProvaFinalizadaRequestDTO {
    private Long id;
    private Long provaId;
    private Long usuarioId;
    private Set<Long> alternativasIds;
}

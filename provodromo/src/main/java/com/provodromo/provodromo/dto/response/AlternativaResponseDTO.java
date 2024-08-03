package com.provodromo.provodromo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AlternativaResponseDTO {
    private Long id;
    private String texto;
    private boolean correta = false;
}

package com.provodromo.provodromo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AlternativaRequestDTO {
    private Long id;
    private String texto;
    private boolean correta = false;
}

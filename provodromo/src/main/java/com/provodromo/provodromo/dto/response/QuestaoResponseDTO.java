package com.provodromo.provodromo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuestaoResponseDTO {
    private Long id;
    private String texto;
    private String dificuldade;
    private double nota;
    private List<AlternativaResponseDTO> alternativas = new ArrayList<>();
}

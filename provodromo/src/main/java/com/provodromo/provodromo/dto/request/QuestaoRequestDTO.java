package com.provodromo.provodromo.dto.request;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuestaoRequestDTO {
    private Long id;
    private String texto;
    private String dificuldade;
    private double nota;
    private List<AlternativaRequestDTO> alternativas = new ArrayList<>();
}

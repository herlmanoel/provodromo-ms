package com.provodromo.provodromo.model;

import com.provodromo.provodromo.model.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tb_resposta_alternativa")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RespostaAlternativa extends BaseModel {
    @ManyToOne
    private Alternativa alternativa;

    @ManyToOne
    @JoinColumn(name = "prova_finalizada_id")
    private ProvaFinalizada provaFinalizada;
}

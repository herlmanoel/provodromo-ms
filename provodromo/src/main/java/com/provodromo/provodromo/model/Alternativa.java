package com.provodromo.provodromo.model;

import com.provodromo.provodromo.model.base.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tb_alternativa")
@Data
public class Alternativa extends BaseModel {

    private String texto;
    private boolean correta = false;

    @ManyToOne
    @JoinColumn(name = "questao_id")
    private Questao questao;
}

package com.provodromo.provodromo.model;

import com.provodromo.provodromo.model.base.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tb_questao")
@Data
public class Questao extends BaseModel {

    private String texto;
    private String dificuldade;
    private double nota;

    @OneToMany(mappedBy = "questao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Alternativa> alternativas = new ArrayList<>();
}
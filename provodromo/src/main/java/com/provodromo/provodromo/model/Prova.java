package com.provodromo.provodromo.model;

import com.provodromo.provodromo.model.base.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tb_prova")
@Data
public class Prova extends BaseModel {

    private String titulo;

    private Long turmaId;

    private Long nota;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "tb_prova_questao",
            joinColumns = @JoinColumn(name = "prova_id"),
            inverseJoinColumns = @JoinColumn(name = "questao_id")
    )
    @EqualsAndHashCode.Exclude
    private Set<Questao> questoes = new HashSet<>();

    public Prova(String titulo, Long turmaId, Long nota, Set<Questao> questoes) {
        this.titulo = titulo;
        this.turmaId = turmaId;
        this.nota = nota;
        this.questoes = questoes;
    }

    public Prova() {
    }
}
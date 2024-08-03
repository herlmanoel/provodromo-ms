package com.provodromo.provodromo.model;

import com.provodromo.provodromo.model.base.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_prova")
@Data
public class Prova extends BaseModel {

    private String titulo;

    @ManyToOne
    @JoinColumn(name = "turma_id")
    private Turma turma;

    private int nota;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "tb_prova_questao",
            joinColumns = @JoinColumn(name = "prova_id"),
            inverseJoinColumns = @JoinColumn(name = "questao_id")
    )
    @EqualsAndHashCode.Exclude
    private Set<Questao> questoes = new HashSet<>();

    public Prova(String titulo, Turma turma, int nota, Set<Questao> questoes) {
        this.titulo = titulo;
        this.turma = turma;
        this.nota = nota;
        this.questoes = questoes;
    }

    public Prova() {
    }
}
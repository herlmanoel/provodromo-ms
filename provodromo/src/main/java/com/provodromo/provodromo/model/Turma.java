package com.provodromo.provodromo.model;

import com.provodromo.provodromo.model.base.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tb_turma")
@Data
public class Turma extends BaseModel {
    private String nome;

    private Long professor;

    public Turma(String nome, Long professor) {
        this.nome = nome;
        this.professor = professor;
    }

    public Turma() {
    }
}
package com.provodromo.institucional.domain;

import com.provodromo.institucional.domain.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tb_aluno")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Aluno extends BaseModel {

    private Long usuarioId;

    private Long usuario;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "aluno_turma",
            joinColumns = @JoinColumn(name = "aluno_id"),
            inverseJoinColumns = @JoinColumn(name = "turma_id"))
    private List<Turma> turmas;

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL)
    private List<Nota> notas;

}

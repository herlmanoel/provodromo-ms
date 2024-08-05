package com.provodromo.provodromo.model;

import com.provodromo.provodromo.model.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tb_prova_finalizada")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProvaFinalizada extends BaseModel {

    private Long usuarioId;

    private Long nota;

    @ManyToOne
    @JoinColumn(name = "prova_id")
    private Prova prova;
}
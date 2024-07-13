package com.provodromo.usuario.entities;

import com.provodromo.usuario.entities.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "tb_tipo_usuario")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class TipoUsuario extends BaseModel {
    private String nome;
}
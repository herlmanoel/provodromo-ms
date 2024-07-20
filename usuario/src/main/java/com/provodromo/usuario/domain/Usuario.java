package com.provodromo.usuario.domain;

import com.provodromo.usuario.domain.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "tb_usuario")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Usuario extends BaseModel {

    private String nome;
    private String email;
    private String senha;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "tipo_usuario_id")
    private TipoUsuario tipoUsuario;
}

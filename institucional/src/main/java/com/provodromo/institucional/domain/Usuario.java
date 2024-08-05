package com.provodromo.institucional.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    private String nome;
    private String email;
    private String tipoUsuario;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

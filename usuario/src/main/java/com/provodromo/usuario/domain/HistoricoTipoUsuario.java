package com.provodromo.usuario.domain;

import com.provodromo.usuario.domain.base.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tb_hist_tipo_usuario")
@Data
public class HistoricoTipoUsuario extends BaseModel {

    private String nome;

    @CreatedDate
    @Column(name = "data_alteracao")
    private LocalDateTime dataAlteracao;

    private Long tipoUsuarioId;
}

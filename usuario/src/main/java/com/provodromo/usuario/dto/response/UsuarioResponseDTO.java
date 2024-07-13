package com.provodromo.usuario.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponseDTO {
        private Long id;
        private String nome;
        private String email;
        private String tipoUsuario;
}

package com.provodromo.usuario.repository;

import com.provodromo.usuario.domain.TipoUsuario;
import com.provodromo.usuario.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Set<Usuario> findAllByTipoUsuarioId(@Param("tipoUsuario")Long id);
    List<Usuario> findByTipoUsuarioId(Long tipoUsuarioId);
    Usuario findByNome(String nome);
    Usuario findByEmail(String nome);
    boolean existsByEmail(String email);
    @Query("SELECT u.tipoUsuario FROM Usuario u WHERE u.email = :email")
    List<TipoUsuario> findTiposUsuarioByEmail(String email);
}

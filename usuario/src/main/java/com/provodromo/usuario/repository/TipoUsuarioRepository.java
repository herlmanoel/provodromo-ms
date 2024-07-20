package com.provodromo.usuario.repository;

import com.provodromo.usuario.domain.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, Long> {
    @Query("SELECT tu FROM TipoUsuario tu WHERE tu.nome = :nome")
    TipoUsuario findByNome(@Param("nome") String nome);
}

package com.provodromo.usuario.repository;

import com.provodromo.usuario.entities.HistoricoTipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricoTipoUsuarioRepository extends JpaRepository<HistoricoTipoUsuario, Long> {
}

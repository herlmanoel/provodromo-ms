package com.provodromo.provodromo.repository;

import com.provodromo.provodromo.model.ProvaFinalizada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProvaFinalizadaRepository extends JpaRepository<ProvaFinalizada, Long> {
    Optional<ProvaFinalizada> findByUsuarioIdAndProvaId(Long usuarioId, Long provaId);

    List<ProvaFinalizada> findByUsuarioId(Long usuarioId);

    List<ProvaFinalizada> findByProvaId(Long provaId);

    @Query("SELECT pf FROM ProvaFinalizada pf JOIN pf.prova p WHERE p.turmaId = :turmaId AND pf.usuarioId = :usuarioId")
    List<ProvaFinalizada> findByUsuarioIdAndTurmaId(Long usuarioId, Long turmaId);
}

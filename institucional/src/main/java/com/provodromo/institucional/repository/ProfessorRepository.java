package com.provodromo.institucional.repository;

import com.provodromo.institucional.domain.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    boolean existsByUsuarioId(Long usuarioId);

    void deleteByUsuarioId(Long usuarioid);

    Optional<Professor> findByUsuarioId(Long id);
}

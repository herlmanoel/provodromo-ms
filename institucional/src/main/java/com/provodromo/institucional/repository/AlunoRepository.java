package com.provodromo.institucional.repository;

import com.provodromo.institucional.domain.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    boolean existsByUsuarioId(Long usuarioId);

    boolean deleteByUsuarioId(Long usuarioId);
}

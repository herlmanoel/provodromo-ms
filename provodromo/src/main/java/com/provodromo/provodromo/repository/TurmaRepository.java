package com.provodromo.provodromo.repository;

import com.provodromo.provodromo.model.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {
    Turma findByNome(String nome);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM tb_turma WHERE usuario_id = :professorId", nativeQuery = true)
    void deleteByProfessor(Long professorId);
}

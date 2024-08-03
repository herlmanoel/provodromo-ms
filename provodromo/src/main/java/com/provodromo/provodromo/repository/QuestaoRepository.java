package com.provodromo.provodromo.repository;

import com.provodromo.provodromo.model.Questao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestaoRepository extends JpaRepository<Questao, Long> {
}

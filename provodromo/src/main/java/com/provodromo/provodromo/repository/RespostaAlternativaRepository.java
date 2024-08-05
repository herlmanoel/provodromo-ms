package com.provodromo.provodromo.repository;

import com.provodromo.provodromo.model.RespostaAlternativa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RespostaAlternativaRepository extends JpaRepository<RespostaAlternativa, Long> {
}

package com.provodromo.provodromo.repository;

import com.provodromo.provodromo.model.Alternativa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlternativaRepository extends JpaRepository<Alternativa, Long> {
}

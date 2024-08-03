package com.provodromo.provodromo.repository;

import com.provodromo.provodromo.model.Prova;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvaRepository extends JpaRepository<Prova, Long> {
}

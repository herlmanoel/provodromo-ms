package com.provodromo.usuario.repository;

import com.provodromo.usuario.entities.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepositotry extends JpaRepository<Worker,Long>{
    
}

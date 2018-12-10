package com.proyectoPaquetes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.proyectoPaquetes.model.Bloqueo;


import java.util.Optional;

@Repository
public interface BloqueoRepository extends JpaRepository<Bloqueo, Long> {

    Bloqueo findFirstByCorreoElectronicoIgnoreCaseContaining(String partialEmailAddress);
}

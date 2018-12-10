package com.proyectoPaquetes.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.proyectoPaquetes.model.Cliente;
import java.util.ArrayList;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Cliente findByCorreoElectronico(String email);
    boolean  existsByCorreoElectronico(String email);
    Cliente findFirstByCorreoElectronicoIgnoreCaseContaining(String partialEmailAddress);

}

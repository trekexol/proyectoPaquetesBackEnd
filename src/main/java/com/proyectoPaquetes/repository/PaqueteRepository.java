package com.proyectoPaquetes.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.proyectoPaquetes.model.Paquete;
import java.util.List;


@Repository
public interface PaqueteRepository extends JpaRepository<Paquete, Long> {

        List<Paquete> findAllByIdOrden(Long idOrden);

    }



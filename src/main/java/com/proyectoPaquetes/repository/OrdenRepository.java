package com.proyectoPaquetes.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.proyectoPaquetes.model.Orden;
import java.util.List;


@Repository
public interface OrdenRepository extends JpaRepository<Orden, Long> {

   Orden findByIdOrden(Long id);

    List<Orden> findAllByIdCliente(Long idCliente);

    boolean existsByIdOrdenAndIdCliente(Long idOrden,Long idCliente);

    boolean existsByIdOrden(Long idOrden);

}

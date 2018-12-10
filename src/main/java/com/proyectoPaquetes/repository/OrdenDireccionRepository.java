package com.proyectoPaquetes.repository;
import com.proyectoPaquetes.model.OrdenDireccion;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.proyectoPaquetes.model.Orden;
import java.util.List;


@Repository
public interface OrdenDireccionRepository extends JpaRepository<OrdenDireccion, Long> {

    OrdenDireccion findByIdOrden(Long idOrden);

    List<OrdenDireccion> findAllByIdOrden(Long idOrden);

    OrdenDireccion findByIdDireccion(Long idDirecionn);

    List<OrdenDireccion> findAllByIdDireccion(Long idDireccion);
}

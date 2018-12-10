package com.proyectoPaquetes.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.proyectoPaquetes.model.Direccion;
import java.util.List;

@Repository
public interface DireccionRepository extends JpaRepository<Direccion, Long> {


    Direccion findByIdClienteAndLongitudAndLatitud(Long idCliente,float longitud,float latitud);

    Direccion findByLongitudAndLatitudAndIdOrden(float longitud,float latitud,Long idOrden);

    boolean  existsByLongitudAndLatitud(float longitud,float latitud);

    List<Direccion> findAllByIdOrden(Long idOrden);

    List<Direccion> findAllByIdClienteAndLongitudAndLatitud(Long idCliente,float longitud,float latitud);


}

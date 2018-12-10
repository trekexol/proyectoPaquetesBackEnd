package com.proyectoPaquetes.Service;

import com.proyectoPaquetes.command.SignUp.OrdenSignUpCommand;
import com.proyectoPaquetes.model.Orden;
import com.proyectoPaquetes.model.Direccion;
import com.proyectoPaquetes.model.OrdenDireccion;
import com.proyectoPaquetes.repository.DireccionRepository;
import com.proyectoPaquetes.repository.OrdenRepository;
import com.proyectoPaquetes.repository.OrdenDireccionRepository;
import com.proyectoPaquetes.response.OrdenResponse;
import com.proyectoPaquetes.response.ListOrdenResponse;
import com.proyectoPaquetes.response.NotifyResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.time.LocalDateTime;


import java.util.ArrayList;


@Slf4j

@Service("OrdenDireccionService")
public class OrdenDireccionService {


    @Autowired
    private OrdenDireccionRepository repository;

    @Autowired
    private OrdenRepository ordenRepository;

    @Autowired
    private DireccionRepository direccionRepository;

    @Autowired
    private DireccionService direccionService;



    public ResponseEntity<Object> register(String idOrden,String idDireccion) {


            try {

                if (ordenRepository.existsById(Long.parseLong(idOrden))) {
                    if (direccionRepository.existsById(Long.parseLong(idDireccion))) {

                          OrdenDireccion orden = new OrdenDireccion();

                            orden.setIdOrden(Long.parseLong(idOrden));
                            orden.setIdDireccion(Long.parseLong(idDireccion));


                            repository.save(orden);

                log.info("Orden Registrado Id = {} , DireccionId = {} ", orden.getIdOrden(), orden.getIdDireccion());

                return ResponseEntity.ok().body(buildNotifyResponse("Orden registrada"));

                    }else{
                        return ResponseEntity.badRequest().body(buildNotifyResponse("id de Direccion invalido"));
                    }
                }else{
                    return ResponseEntity.badRequest().body(buildNotifyResponse("id de Orden invalido"));
                }
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(buildNotifyResponse("*Ocurrio un Error* :La orden no se pudo registrar en el sistema."));

            }


    }



    public Boolean estaAgregadaLaOrdenConEstaDireccion (String idOrden,String idDireccion){
        try{
            List<OrdenDireccion> ordenDireccion;

            ordenDireccion = repository.findAllByIdDireccion(Long.parseLong(idDireccion));

            boolean resultado =false;

            if(ordenDireccion!=null) {

                for (OrdenDireccion i : ordenDireccion) {
                    if (i.getIdOrden() == Long.parseLong(idOrden)) {
                        resultado = true;
                    }
                }

                return resultado;
            }else
                return resultado;
        }catch(Exception e){
            return false;

        }
    }



    public ResponseEntity<Object> buscarOrdenesDeUnaDireccion(String idCliente,String latitud,String longitud){

        try{

        Direccion direccion;

        direccion = buscarDireccion(Long.parseLong(idCliente),Float.parseFloat(latitud),Float.parseFloat(longitud));

        if(direccion != null) {

          List<OrdenDireccion> ordenDireccions;

          ordenDireccions = repository.findAllByIdDireccion(direccion.getIdDireccion());


            List<OrdenResponse> ordenes = new ArrayList<>();

            for (OrdenDireccion i : ordenDireccions) {

                Orden orden;

                orden = ordenRepository.findByIdOrden(i.getIdOrden());

                if (orden != null) {

                    OrdenResponse respuesta = new OrdenResponse();

                    respuesta.setIdOrden(String.valueOf(orden.getIdOrden()));
                    respuesta.setIdCliente(String.valueOf(orden.getIdCliente()));
                    respuesta.setDireccionEntrega(orden.getDireccionEntrega());
                    respuesta.setDireccionRecoleccion(orden.getDireccionRecoleccion());

                    ordenes.add(respuesta);

                }

            }

            ListOrdenResponse response = new ListOrdenResponse();

            response.setOrdenes(ordenes);
            return ResponseEntity.ok().body(response);

        } else
        return ResponseEntity.badRequest().body(buildNotifyResponse("No se encontraron Ordenes en este Punto"));

    }catch(Exception e){
        return ResponseEntity.badRequest().body(buildNotifyResponse("Ocurrio un error al buscar la Orden"));

    }
    }

    public Direccion buscarDireccion(Long idCliente,float latitud,float longitud){

        try{
            Direccion direccion;


            direccion= direccionRepository.findByIdClienteAndLongitudAndLatitud(idCliente,longitud,latitud);


            if (direccion == null)
                return null;
            else
                return direccion;

        }catch(Exception e){
            log.info("Ocurrio un error buscando la direccion");

            return null;


        }
    }

    public ResponseEntity<Object> eliminarOrden(String id) {
        try {

            repository.deleteById(Long.parseLong(id));

            return ResponseEntity.ok().body(buildNotifyResponse("La Orden ha sido eliminada"));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(buildNotifyResponse("La Orden no pudo ser eliminada"));

        }
    }




    private NotifyResponse buildNotifyResponse(String message) { //MUESTRA UN MENSAJE DE NOTIFICACIÓN
        NotifyResponse respuesta = new NotifyResponse();
        respuesta.setMessage(message);
        respuesta.setTimestamp(LocalDateTime.now());
        return respuesta;
    }
}

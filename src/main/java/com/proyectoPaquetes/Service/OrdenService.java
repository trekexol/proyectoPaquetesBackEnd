package com.proyectoPaquetes.Service;

import com.proyectoPaquetes.command.SignUp.OrdenSignUpCommand;
import com.proyectoPaquetes.model.Orden;
import com.proyectoPaquetes.model.OrdenDireccion;
import com.proyectoPaquetes.model.Direccion;
import com.proyectoPaquetes.model.Paquete;
import com.proyectoPaquetes.repository.DireccionRepository;
import com.proyectoPaquetes.repository.PaqueteRepository;
import com.proyectoPaquetes.repository.OrdenDireccionRepository;
import com.proyectoPaquetes.repository.OrdenRepository;
import com.proyectoPaquetes.repository.ClienteRepository;
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

@Service("OrdenService")
public class OrdenService {

        @Autowired
        private ClienteRepository clienteRepository;

        @Autowired
        private OrdenRepository ordenRepository;

        @Autowired
        private DireccionRepository direccionRepository;

        @Autowired
        private OrdenDireccionService ordenDireccionService;

        @Autowired
        private OrdenDireccionRepository ordenDireccionRepository;

        @Autowired
        private PaqueteRepository paqueteRepository;



        public ResponseEntity<Object> register(OrdenSignUpCommand command,String idCliente) {
            log.debug("About to be processed [{}]", command);

               try {

                   if (clienteRepository.existsById(Long.parseLong(idCliente))) {

                       Orden orden = new Orden();

                    orden.setIdOrden(System.currentTimeMillis());
                    orden.setIdCliente(Long.parseLong(idCliente));

                    orden.setDireccionEntrega(command.getDireccionEntrega());
                    orden.setDireccionRecoleccion(command.getDireccionRecoleccion());



                    ordenRepository.save(orden);


                    log.info("Orden Registrado Id = {} , ClienteId = {} ", orden.getIdOrden(), orden.getIdCliente());

                    return ResponseEntity.ok().body(buildNotifyResponse(String.valueOf(orden.getIdOrden())));

                   }else{
                       return ResponseEntity.badRequest().body(buildNotifyResponse("id invalido"));
                   }

                } catch (Exception e) {
                    return ResponseEntity.badRequest().body(buildNotifyResponse("*Ocurrio un Error* :La orden no se pudo registrar en el sistema."));

                }


        }






    public ResponseEntity<Object> buscarOrdenDadoLatLng(String idCliente,String longitud,String latitud){
         try{
        Direccion direccion;

        direccion = direccionRepository.findByIdClienteAndLongitudAndLatitud(Long.parseLong(idCliente),Float.parseFloat(longitud),Float.parseFloat(latitud));

       if(direccion!=null) {
           Orden orden;

           orden = ordenRepository.findByIdOrden(direccion.getIdOrden());

           if (orden != null) {

               OrdenResponse respuesta = new OrdenResponse();

               respuesta.setIdOrden(String.valueOf(orden.getIdOrden()));
               respuesta.setIdCliente(String.valueOf(orden.getIdCliente()));
               respuesta.setDireccionEntrega(orden.getDireccionEntrega());
               respuesta.setDireccionRecoleccion(orden.getDireccionRecoleccion());

               return ResponseEntity.ok(respuesta);
           } else
               return ResponseEntity.badRequest().body(buildNotifyResponse("No se encontro la orden"));

       }else
           return ResponseEntity.badRequest().body(buildNotifyResponse("No se encontro la orden"));

         }catch(Exception e){
        return ResponseEntity.badRequest().body(buildNotifyResponse("Ocurrio un error al buscar la Orden"));

        }
    }


    public ResponseEntity<Object> buscarOrdenesDeUnCliente(String idCliente){
        try{

             List<Orden> orden;

                orden = ordenRepository.findAllByIdCliente(Long.parseLong(idCliente));

                if (orden != null) {



                   List<OrdenResponse> listResponses = new ArrayList<>();
                    orden.forEach( i-> {

                                OrdenResponse ordenResponse = new OrdenResponse();

                                ordenResponse.setIdCliente(String.valueOf(i.getIdCliente()));
                                ordenResponse.setIdOrden(String.valueOf(i.getIdOrden()));
                                ordenResponse.setDireccionRecoleccion(i.getDireccionRecoleccion());
                                ordenResponse.setDireccionEntrega(i.getDireccionEntrega());


                                listResponses.add(ordenResponse);
                            }
                        );
                       ListOrdenResponse response = new ListOrdenResponse();

                        response.setOrdenes(listResponses);

                       return ResponseEntity.ok(response);

                    } else
                    return ResponseEntity.badRequest().body(buildNotifyResponse("No se Encontraron Ordenes de este Cliente"));


        }catch(Exception e){
            return ResponseEntity.badRequest().body(buildNotifyResponse("-*Error*- No se Encontraron Ordenes de este Cliente"));


        }
    }


    public ResponseEntity<Object> eliminarOrden(String id) {
        try {

             eliminarTodasLasDireccionesDeUnaOrden(Long.parseLong(id));

             eliminarTodasLasOrdenDireccionesDeUnaOrden(Long.parseLong(id));

             eliminarTodosLosPaquetesDeUnaOrden(Long.parseLong(id));

             if(ordenRepository.existsByIdOrden(Long.parseLong(id))) {
                 ordenRepository.deleteById(Long.parseLong(id));
             }else{
                 return ResponseEntity.badRequest().body(buildNotifyResponse("No se encontro la Orden"));

             }
          return ResponseEntity.ok().body(buildNotifyResponse("La Orden ha sido eliminada"));

        }catch(Exception e){
            return ResponseEntity.badRequest().body(buildNotifyResponse("La Orden no pudo ser eliminada"));

        }
    }


    public void eliminarTodasLasDireccionesDeUnaOrden(Long idOrden){

            try{

        List<Direccion> direcciones = null;

       direcciones = direccionRepository.findAllByIdOrden(idOrden);

        if(!(direcciones.isEmpty())) {

            for (Direccion i : direcciones) {

                direccionRepository.deleteById(i.getIdDireccion());

            }
        }

        }catch(Exception e){
            log.info("Ocurrio un error borrando todas las Direcciones");

        }

    }

    public void eliminarTodasLasOrdenDireccionesDeUnaOrden(Long idOrden){
        try {

            List<OrdenDireccion> ordenDireccions;

            ordenDireccions = ordenDireccionRepository.findAllByIdOrden(idOrden);

            if(!(ordenDireccions.isEmpty())) {


                for (OrdenDireccion i : ordenDireccions) {


                    ordenDireccionRepository.deleteById(i.getIdOrden());

                }
            }

        }catch(Exception e){
            log.info("Ocurrio un error borrando todas las OrdenDirecciones");

        }

    }

    public void eliminarTodosLosPaquetesDeUnaOrden(Long idOrden){
        try {
            List<Paquete> paquetes;

            paquetes = paqueteRepository.findAllByIdOrden(idOrden);

        if(!(paquetes.isEmpty())) {
            for (Paquete i : paquetes) {


                paqueteRepository.deleteById(i.getIdPaquete());

            }
        }

        }catch(Exception e){
            log.info("Ocurrio un error borrando todas los paquetes");

        }

    }





    private NotifyResponse buildNotifyResponse(String message) { //MUESTRA UN MENSAJE DE NOTIFICACIÓN
        NotifyResponse respuesta = new NotifyResponse();
        respuesta.setMessage(message);
        respuesta.setTimestamp(LocalDateTime.now());
        return respuesta;
    }
    }

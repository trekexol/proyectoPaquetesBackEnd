package com.proyectoPaquetes.Service;

import com.proyectoPaquetes.command.SignUp.DireccionSignUpCommand;
import com.proyectoPaquetes.model.Direccion;
import com.proyectoPaquetes.repository.DireccionRepository;
import com.proyectoPaquetes.repository.ClienteRepository;
import com.proyectoPaquetes.repository.OrdenRepository;
import com.proyectoPaquetes.response.DireccionResponse;
import com.proyectoPaquetes.response.ListDireccionResponse;
import com.proyectoPaquetes.response.NotifyResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

@Slf4j

@Service("DireccionService")
public class DireccionService {

    @Autowired
    private DireccionRepository direccionRepository;

    @Autowired
    private OrdenRepository ordenRepository;

    @Autowired
    private OrdenDireccionService ordenDireccionService;



    public ResponseEntity<Object> register(DireccionSignUpCommand command,String idOrden,String idCliente) {
        log.debug("About to be processed [{}]", command);
        try {
          //Valida que el Cliente y La Orden esten registrados
        if (ordenRepository.existsByIdOrdenAndIdCliente(Long.parseLong(idOrden),Long.parseLong(idCliente))) {
            long existeLaDireccion = 0;

            //Verifica que el Cliente haya registrado estas coordenadas
            Direccion yaExisteLaDireccion = direccionRepository.findByIdClienteAndLongitudAndLatitud(Long.parseLong(idCliente),command.getLongitud(),command.getLatitud());

            if(yaExisteLaDireccion != null) {
                //Verifica que la Orden ya tenga esas coordenadas registradas
                Direccion ordenYaAgregada = verificarDireccion(command.getLatitud(), command.getLongitud(), Long.parseLong(idOrden));

                if (ordenYaAgregada != null) {
                    return ResponseEntity.badRequest().body(buildNotifyResponse("Ya la Orden posee esta Direccion"));

                    }else{
                    log.info("id direccion: {}",yaExisteLaDireccion.getIdDireccion());
                    existeLaDireccion=yaExisteLaDireccion.getIdDireccion();
                    return objectRegister(command, idOrden,idCliente,existeLaDireccion);
                }
            } else {
                return objectRegister(command, idOrden,idCliente,existeLaDireccion);

            }
        }else{
            return ResponseEntity.badRequest().body(buildNotifyResponse("El Cliente y la Orden no estan registrados"));
        }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(buildNotifyResponse("*Ocurrio un Error* La direccion no se pudo registrar en el sistema."));

        }
    }

    public ResponseEntity<Object> objectRegister(DireccionSignUpCommand command,String idOrden,String idCliente,Long existeLaDireccion) {


        try {

                    Direccion direccion = new Direccion();

                    if(existeLaDireccion!=0) {
                        direccion.setIdDireccion(existeLaDireccion);
                    }else {
                        direccion.setIdDireccion(System.currentTimeMillis());
                    }
                    direccion.setIdOrden(Long.parseLong(idOrden));
                    direccion.setIdCliente(Long.parseLong(idCliente));
                    direccion.setDireccion1(command.getDireccion1());
                    direccion.setDireccion2(command.getDireccion2());
                    direccion.setCodigoPostal(command.getCodigoPostal());
                    direccion.setCiudad(command.getCiudad());
                    direccion.setPais(command.getPais());
                    direccion.setTipoDeDireccion(command.getTipoDeDireccion());
                    direccion.setLongitud(command.getLongitud());
                    direccion.setLatitud(command.getLatitud());


                    direccionRepository.save(direccion);


                    ordenDireccionService.register(idOrden,String.valueOf(direccion.getIdDireccion())); //registra los id de la orden y la direccion

                    log.info("Direccion Registrada Id = {} , OrdenId = {} ", direccion.getIdDireccion(), direccion.getIdOrden());

                    return ResponseEntity.ok().body(buildNotifyResponse("Direccion registrada : "+direccion.getIdDireccion()));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(buildNotifyResponse("*Ocurrio un Error* : La direccion no se pudo registrar en el sistema."));

        }
    }




    public ResponseEntity<Object>  buscarDireccionesDeUnaOrden(String id){
        try{

            List<Direccion> direccion;

            direccion = direccionRepository.findAllByIdOrden(Long.parseLong(id));

            if (direccion != null) {



                List<DireccionResponse> listResponses = new ArrayList<>();
                direccion.forEach( i-> {

                    DireccionResponse respuesta = new DireccionResponse();

                    respuesta.setIdDireccion(String.valueOf(i.getIdDireccion()));
                    respuesta.setIdOrden(String.valueOf(i.getIdOrden()));
                    respuesta.setIdCliente(String.valueOf(i.getIdCliente()));
                    respuesta.setDireccion1(i.getDireccion1());
                    respuesta.setDireccion2(i.getDireccion2());
                    respuesta.setCiudad(i.getCiudad());
                    respuesta.setCodigoPostal(i.getCodigoPostal());
                    respuesta.setTipoDeDireccion(i.getTipoDeDireccion());
                    respuesta.setPais(i.getPais());

                    respuesta.setLatitud(i.getLatitud());
                    respuesta.setLongitud(i.getLongitud());


                            listResponses.add(respuesta);
                        }
                );
                ListDireccionResponse response = new ListDireccionResponse();

                response.setDirecciones(listResponses);

                return ResponseEntity.ok(response);

            } else
                return ResponseEntity.badRequest().body(buildNotifyResponse("No se Encontraron Direcciones de esta Orden"));


        }catch(Exception e){
            return ResponseEntity.badRequest().body(buildNotifyResponse("Ocurrio un Error al buscar las direcciones"));


        }
    }


   public Direccion verificarDireccion(float latitud,float longitud,Long idOrden){

    try{
        Direccion direccion;


         direccion= direccionRepository.findByLongitudAndLatitudAndIdOrden(longitud,latitud,idOrden);


        if (direccion == null)
            return null;
        else
            return direccion;

    }catch(Exception e) {
        log.info("Ocurrio un error buscando la direccion");

        return null;
    }

    }







    public ResponseEntity<Object> eliminarDireccion(String id) {
        try {

            direccionRepository.deleteById(Long.parseLong(id));

            return ResponseEntity.ok().body(buildNotifyResponse("La direccion ha sido eliminada"));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(buildNotifyResponse("La direccion no pudo ser eliminada"));

        }
    }



    private NotifyResponse buildNotifyResponse(String message) { //MUESTRA UN MENSAJE DE NOTIFICACIÓN
        NotifyResponse respuesta = new NotifyResponse();
        respuesta.setMessage(message);
        respuesta.setTimestamp(LocalDateTime.now());
        return respuesta;
    }
}

package com.proyectoPaquetes.Service;


import com.proyectoPaquetes.command.SignUp.PaqueteSignUpCommand;
import com.proyectoPaquetes.model.Paquete;
import com.proyectoPaquetes.repository.OrdenRepository;
import com.proyectoPaquetes.repository.PaqueteRepository;
import com.proyectoPaquetes.response.NotifyResponse;
import com.proyectoPaquetes.response.PaqueteResponse;
import com.proyectoPaquetes.response.ListPaqueteResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.time.LocalDateTime;


import java.util.ArrayList;

@Slf4j

@Service("PaqueteService")
public class PaqueteService {


        @Autowired
        private PaqueteRepository paqueteRepository;
        @Autowired
        private OrdenRepository ordenRepository;


            public ResponseEntity<Object> register(PaqueteSignUpCommand command,String idOrden) {
               try{
                log.debug("About to be processed [{}]", command);

                if (ordenRepository.existsByIdOrden(Long.parseLong(idOrden))) {
                    try {


                        Paquete paquete = new Paquete();

                        paquete.setIdPaquete(System.currentTimeMillis());
                        paquete.setIdOrden(Long.parseLong(idOrden));
                        paquete.setNombreApellidoEntrega(command.getNombreApellidoEntrega());
                        paquete.setPesoKgs(Double.parseDouble(command.getPesoKgs()));
                        paquete.setDescripcionPaquete(command.getDescripcionPaquete());

                        paqueteRepository.save(paquete);

                        log.info("Paquete Registrado Id = {} , Orden Id = {} ", paquete.getIdPaquete(),paquete.getIdOrden());

                        return ResponseEntity.ok().body(buildNotifyResponse("Paquete registrado : "+paquete.getIdPaquete()));


                    } catch (Exception e) {
                        return ResponseEntity.badRequest().body(buildNotifyResponse("*Ocurrio un Error* : El paquete no se pudo registrar en el sistema."));

                    }
                }else{
                    return ResponseEntity.badRequest().body(buildNotifyResponse("id invalido"));
                }

               } catch (Exception e) {
                   return ResponseEntity.badRequest().body(buildNotifyResponse("*Ocurrio un Error* :El Paquete no se pudo registrar en el sistema."));

               }
            }



    public ResponseEntity<Object> buscarPaquetesDeUnaOrden(String idOrden){
        try{

            List<Paquete> paquete;

            paquete = paqueteRepository.findAllByIdOrden(Long.parseLong(idOrden));

            if (paquete != null) {

                return ResponseEntity.ok().body(buildNotifyResponse("entro "));

               // return ResponseEntity.ok(response);

               /* List<PaqueteResponse> listResponses = new ArrayList<>();
                paquete.forEach( i-> {

                    PaqueteResponse paqueteResponse = new PaqueteResponse();

                    paqueteResponse.setIdPaquete(String.valueOf(i.getIdPaquete()));
                    paqueteResponse.setIdOrden(String.valueOf(i.getIdOrden()));
                    paqueteResponse.setPesoKgs(String.valueOf(i.getPesoKgs()));
                    paqueteResponse.setNombreApellidoEntrega(i.getNombreApellidoEntrega());
                    paqueteResponse.setDescripcionPaquete(i.getDescripcionPaquete());


                            listResponses.add(paqueteResponse);
                        }
                );
                ListPaqueteResponse response = new ListPaqueteResponse();

                response.setPaquetes(listResponses);

                return ResponseEntity.ok(response);*/

            } else
                return ResponseEntity.badRequest().body(buildNotifyResponse("No se Encontraron Paquetes en la Orden"));


        }catch(Exception e){
            return ResponseEntity.badRequest().body(buildNotifyResponse("-*Error*- No se Encontraron Paquetes de esta orden"));


        }
    }





    public ResponseEntity<Object> eliminarPaquete(String id) {
        try {

            paqueteRepository.deleteById(Long.parseLong(id));

            return ResponseEntity.ok().body(buildNotifyResponse("El paquete ha sido eliminado"));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(buildNotifyResponse("El paquete no pudo ser eliminado "));

        }
    }





        private NotifyResponse buildNotifyResponse(String message) { //MUESTRA UN MENSAJE DE NOTIFICACIÓN
            NotifyResponse respuesta = new NotifyResponse();
            respuesta.setMessage(message);
            respuesta.setTimestamp(LocalDateTime.now());
            return respuesta;
        }
    }





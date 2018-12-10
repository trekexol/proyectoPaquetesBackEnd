package com.proyectoPaquetes.Service;



import com.proyectoPaquetes.command.ClienteLoginCommand;
import com.proyectoPaquetes.command.SignUp.BloqueoSignUpCommand;
import com.proyectoPaquetes.command.ValidationRules;
import com.proyectoPaquetes.model.Cliente;
import com.proyectoPaquetes.model.Bloqueo;
import com.proyectoPaquetes.repository.BloqueoRepository;
import com.proyectoPaquetes.response.ClienteResponse;
import com.proyectoPaquetes.response.NotifyResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j

@Service("BloqueoService")
public class BloqueoService {

    @Autowired
    private BloqueoRepository bloqueoRepository;

    private static int INTENTOSPERMITIDOS = 3;



    public ResponseEntity<Object> register(Bloqueo command) {
        log.debug("About to be processed [{}]", command);

                try {

                    Bloqueo user = new Bloqueo();

                        user.setId(command.getId());
                        user.setCorreoElectronico(command.getCorreoElectronico());
                        user.setCantidadDeIntentos(command.getCantidadDeIntentos());
                        user.setEstadoDeBloqueo(false);

                        bloqueoRepository.save(user);

                        log.info("Bloqueo Registrado Id = {} ", user.getId());

                        return ResponseEntity.ok().body(buildNotifyResponse("Bloqueo registrado "));

                }catch(Exception e){
                   return ResponseEntity.badRequest().body(buildNotifyResponse("no se pudo registrar "));

                }
            }




    public ResponseEntity<Object> modificar(Bloqueo command) {
        log.debug("About to be processed [{}]", command);

        try {

            int intentos=0;


        if(command.isEstadoDeBloqueo()==false) {
            bloqueoRepository.deleteById(command.getId());

            if (command.getCantidadDeIntentos() < INTENTOSPERMITIDOS) {
                intentos += command.getCantidadDeIntentos() + 1;
                command.setCantidadDeIntentos(intentos);
                log.info("Cantidad de intentos = {} ", intentos);
            }
            if (command.getCantidadDeIntentos() == INTENTOSPERMITIDOS) {
                Bloqueo user = new Bloqueo();

                user.setId(command.getId());
                user.setCorreoElectronico(command.getCorreoElectronico());
                user.setCantidadDeIntentos(command.getCantidadDeIntentos());
                user.setEstadoDeBloqueo(true);

                bloqueoRepository.save(user);

                return ResponseEntity.badRequest().body(buildNotifyResponse("Usuario Bloqueado"));

            }


            Bloqueo user = new Bloqueo();

            user.setId(command.getId());
            user.setCorreoElectronico(command.getCorreoElectronico());
            user.setCantidadDeIntentos(command.getCantidadDeIntentos());
            user.setEstadoDeBloqueo(command.isEstadoDeBloqueo());


            bloqueoRepository.save(user);

            log.info("Bloqueo modificado Id = {} ", user.getId());

            return ResponseEntity.ok().body(buildNotifyResponse("Bloqueo modificado "));
        }else
            return ResponseEntity.badRequest().body(buildNotifyResponse("Su Usuario fue Bloqueado"));

        }catch(Exception e){
            return ResponseEntity.badRequest().body(buildNotifyResponse("no se pudo registrar en el sistema."));

        }
    }

    public ResponseEntity<Object> RegistrarBloqueo(Bloqueo user) {

    try{

        Bloqueo bloqueo = new Bloqueo();

        bloqueo = bloqueoRepository.findFirstByCorreoElectronicoIgnoreCaseContaining(user.getCorreoElectronico());

        if(bloqueo != null){
            if(bloqueo.isEstadoDeBloqueo()==true){
                return ResponseEntity.badRequest().body(buildNotifyResponse("Su usuario esta Bloqueado"));

            }else{
                return modificar(bloqueo);
            }
        }else{
            return register(user);
        }

    }catch(Exception e){
        return ResponseEntity.badRequest().body(buildNotifyResponse("no se pudo verificar en el sistema."));

    }


    }

    public boolean VerificarSiEstaBloqueado(String correo) {

        try{

            Bloqueo bloqueo = new Bloqueo();
           bloqueo = bloqueoRepository.findFirstByCorreoElectronicoIgnoreCaseContaining(correo);

            if(bloqueo != null){
                if(bloqueo.isEstadoDeBloqueo()==true){
                    return false;

                }else{
                    return true;
                }
            }else{
                return true;
            }

        }catch(Exception e){
            log.info("Ocurrio un Error");
            return false;

        }


    }

    public void borrarBloqueo(String correo){

        try{
            Bloqueo bloqueo =new Bloqueo();
            bloqueo = bloqueoRepository.findFirstByCorreoElectronicoIgnoreCaseContaining(correo);

        if(bloqueo!=null)
            bloqueoRepository.deleteById(bloqueo.getId());


        }catch(Exception e){
        log.info("Ocurrio un Error en borrar el bloqueo");


         }
    }


    private NotifyResponse buildNotifyResponse(String message) { //MUESTRA UN MENSAJE DE NOTIFICACIÓN
        NotifyResponse respuesta = new NotifyResponse();
        respuesta.setMessage(message);
        respuesta.setTimestamp(LocalDateTime.now());
        return respuesta;
    }



        }




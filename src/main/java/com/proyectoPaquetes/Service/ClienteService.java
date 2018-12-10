package com.proyectoPaquetes.Service;


import com.proyectoPaquetes.command.ClienteLoginCommand;
import com.proyectoPaquetes.command.SignUp.BloqueoSignUpCommand;
import com.proyectoPaquetes.command.SignUp.ClienteSignUpCommand;
import com.proyectoPaquetes.model.Cliente;
import com.proyectoPaquetes.model.Bloqueo;
import com.proyectoPaquetes.repository.ClienteRepository;
import com.proyectoPaquetes.response.ClienteResponse;
import com.proyectoPaquetes.response.NotifyResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.proyectoPaquetes.command.Validation;
import com.proyectoPaquetes.command.ClienteUpdateCommand;

import java.time.LocalDateTime;

@Slf4j

@Service("ClienteService")
public class ClienteService {



    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private BloqueoService bloqueo;


    Validation validation = new Validation();




    public ResponseEntity<Object> login(ClienteLoginCommand command) {
        try {
            log.debug("About to process [{}]", command);
            Cliente u = clienteRepository.findFirstByCorreoElectronicoIgnoreCaseContaining(command.getCorreoElectronico());
            if (u == null) {
                log.info("Cannot find user with email={}", command.getCorreoElectronico());

                return ResponseEntity.badRequest().body(buildNotifyResponse("Dirección de correo no válida."));
            } else {
                if (u.getContrasena().equals(command.getContrasena())) {
                    if (bloqueo.VerificarSiEstaBloqueado(u.getCorreoElectronico())) {

                        bloqueo.borrarBloqueo(u.getCorreoElectronico());

                        log.info("Successful login for user={}", u.getIdCliente());
                        ClienteResponse respuesta = new ClienteResponse();
                        respuesta.setIdCliente(String.valueOf(u.getIdCliente()));
                        respuesta.setNombre(u.getNombre());
                        respuesta.setApellido(u.getApellido());
                        respuesta.setCorreoElectronico(u.getCorreoElectronico());
                        respuesta.setContrasena(u.getContrasena());
                        respuesta.setFechaNacimiento(String.valueOf(u.getFechaNacimiento()));

                        return ResponseEntity.ok(respuesta);
                    } else
                        return ResponseEntity.badRequest().body(buildNotifyResponse("Su usuario esta Bloqueado"));

                } else {
                    log.info("{} is not valid password for user {}", command.getContrasena(), u.getIdCliente());
                    Bloqueo user = new Bloqueo();
                    user.setId(u.getIdCliente());
                    user.setCorreoElectronico(u.getCorreoElectronico());
                    user.setCantidadDeIntentos(1);

                    return bloqueo.RegistrarBloqueo(user);
                }
            }
        }catch(Exception e){
            return ResponseEntity.badRequest().body(buildNotifyResponse("Ocurrio un error al hacer login"));

        }
    }


//-----------------------------------------------------------------------------------------------------------
    //SERVICIO PARA REGISTRAR USUARIO

    public ResponseEntity<Object> register(ClienteSignUpCommand command) {
        log.debug("About to be processed [{}]", command);



        if (clienteRepository.existsByCorreoElectronico(command.getCorreoElectronico())) {
            log.info("La dirección de correo {} ya se encuentra en la base de datos.", command.getCorreoElectronico());

            return ResponseEntity.badRequest().body(buildNotifyResponse("El usuario ya se encuentra registrado en el sistema."));
        } else {

                if (!command.getContrasena().equals(command.getConfirmacioncontrasena())) {
                    log.info("Las contrasenas no coinciden");
                    return ResponseEntity.badRequest().body(buildNotifyResponse("Las contrasenas no coinciden"));
                } else {
                    try {


                    if(validation.esMayorDeEdad(command.getFechaNacimiento())){

                    Cliente user = new Cliente();

                        user.setIdCliente(System.currentTimeMillis());
                        user.setNombre(command.getNombre());
                        user.setApellido(command.getApellido());
                        user.setCorreoElectronico(command.getCorreoElectronico());
                        user.setContrasena(command.getContrasena());
                        user.setFechaNacimiento(command.getFechaNacimiento());

                        clienteRepository.save(user);

                        log.info("Usuario Registrado Id = {} ", user.getIdCliente());

                        return ResponseEntity.ok().body(buildNotifyResponse("Cliente registrado "));
                    }else{
                        return ResponseEntity.badRequest().body(buildNotifyResponse("El Usuario no es mayor de edad "));

                    }

                }catch(Exception e){
                log.info("Contrasena Invalida ", command.getContrasena());
                return ResponseEntity.badRequest().body(buildNotifyResponse("*Contrasena Invalida* : El usuario no se pudo registrar en el sistema."));

                    }
                }
            }

    }


    public ResponseEntity<Object> update(ClienteUpdateCommand command, String id) {
        log.debug("About to process [{}]", command);
        if (!clienteRepository.existsById(Long.parseLong(id))) {
            log.info("Cannot find user with ID={}", id);
            return ResponseEntity.badRequest().body(buildNotifyResponse("id invalido"));
        } else {
            if(validation.esMayorDeEdad(command.getFechaNacimiento())){
            Cliente user = new Cliente();
            clienteRepository.deleteById(Long.parseLong(id));

            user.setIdCliente(Long.parseLong(id));
            user.setNombre(command.getNombre());
            user.setApellido(command.getApellido());
            user.setCorreoElectronico(command.getCorreoElectronico());
            user.setContrasena(command.getContrasena());
            user.setFechaNacimiento(command.getFechaNacimiento());

            clienteRepository.save(user);

            log.info("Updated user with ID={}", user.getIdCliente());

            return ResponseEntity.ok().body(buildNotifyResponse("Usuario Actualizado"));
            }else{
                return ResponseEntity.badRequest().body(buildNotifyResponse("El Usuario no es mayor de edad "));

            }
            }
    }


    public ResponseEntity<Object> eliminarCliente(String id) {
        try {

            clienteRepository.deleteById(Long.parseLong(id));

            return ResponseEntity.ok().body(buildNotifyResponse("El usuario ha sido eliminado"));
        }catch(Exception e){
                return ResponseEntity.badRequest().body(buildNotifyResponse("El Usuario no pudo ser eliminado "));

            }
    }



    private NotifyResponse buildNotifyResponse(String message) { //MUESTRA UN MENSAJE DE NOTIFICACIÓN
        NotifyResponse respuesta = new NotifyResponse();
        respuesta.setMessage(message);
        respuesta.setTimestamp(LocalDateTime.now());
        return respuesta;
    }
}

package com.proyectoPaquetes.controller;


import com.proyectoPaquetes.command.ClienteDesbloqueo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import com.proyectoPaquetes.Service.ClienteService;

import com.proyectoPaquetes.command.ClienteLoginCommand;
import com.proyectoPaquetes.command.SignUp.ClienteSignUpCommand;
import com.proyectoPaquetes.command.ClienteUpdateCommand;

@Slf4j

@CrossOrigin
@RestController
@RequestMapping(value = "/cliente", produces = "application/json")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ClienteService clienteRepository;



    @RequestMapping(value = "/registrar", consumes = "application/json", method = RequestMethod.POST)
    public ResponseEntity register(@Valid @RequestBody ClienteSignUpCommand command) {
        return clienteService.register(command);
    }

    @RequestMapping(value = "/login", consumes = "application/json", method = RequestMethod.POST)
    public ResponseEntity login(@Valid @RequestBody ClienteLoginCommand command) {

        return clienteService.login(command);
    }


    @RequestMapping(value = "/actualizar/{id}", consumes = "application/json", method = RequestMethod.PUT)
    public ResponseEntity update(@Valid @RequestBody ClienteUpdateCommand command, @PathVariable("id") String id) {
        return clienteService.update(command, id);
    }

    @RequestMapping(value = "/desbloquear", consumes = "application/json", method = RequestMethod.POST)
    public ResponseEntity recuperar(@Valid @RequestBody ClienteDesbloqueo command) {

        return clienteService.desbloqueo(command);
    }
}

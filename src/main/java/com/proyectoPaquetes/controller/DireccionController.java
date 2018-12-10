package com.proyectoPaquetes.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.proyectoPaquetes.Service.DireccionService;
import com.proyectoPaquetes.Service.OrdenDireccionService;

import com.proyectoPaquetes.command.SignUp.DireccionSignUpCommand;

@Slf4j

@CrossOrigin
@RestController
@RequestMapping(value = "/direccion", produces = "application/json")
public class DireccionController {


        @Autowired
        private DireccionService service;
        @Autowired
        private OrdenDireccionService ordenDireccionService;




        @RequestMapping(value = "/registrar/{idCliente}/{idOrden}", consumes = "application/json", method = RequestMethod.POST)
        public ResponseEntity register(@Valid @RequestBody DireccionSignUpCommand command,@PathVariable("idCliente") String idCliente, @PathVariable("idOrden") String id) {
            return service.register(command,id,idCliente);
        }


    @RequestMapping(value = "/eliminar/{id}", consumes = "application/json", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") String id) {
        return service.eliminarDireccion(id);
    }

    @RequestMapping(value = "/buscar/{idCliente}", consumes = "application/json", method = RequestMethod.GET)
    public ResponseEntity buscarOrdenesDeUnCliente(@PathVariable("idCliente") String id) {

        return service.buscarDireccionesDeUnaOrden(id);
    }

    @RequestMapping(value = "/buscar/{idCliente}/{lat}/{lng}", consumes = "application/json", method = RequestMethod.GET)
    public ResponseEntity buscarOrdenesDeUnaDireccion(@PathVariable("idCliente") String idCliente,@PathVariable("lat") String latitud,@PathVariable("lng") String longitud) {

        return ordenDireccionService.buscarOrdenesDeUnaDireccion(idCliente,latitud,longitud);
    }


    }

package com.proyectoPaquetes.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.proyectoPaquetes.Service.OrdenService;

import com.proyectoPaquetes.command.SignUp.OrdenSignUpCommand;

@Slf4j

@CrossOrigin
@RestController
@RequestMapping(value = "/orden", produces = "application/json")
public class OrdenController {



        @Autowired
        private OrdenService ordenService;



        @RequestMapping(value = "/registrar", consumes = "application/json", method = RequestMethod.POST)
        public ResponseEntity register(@Valid @RequestBody OrdenSignUpCommand command) {
            return ordenService.register2(command);
        }


        @RequestMapping(value = "/eliminar/{id}", consumes = "application/json", method = RequestMethod.DELETE)
        public ResponseEntity delete(@PathVariable("id") String id) {
                return ordenService.eliminarOrden(id);
        }

        @RequestMapping(value = "/buscar/{idCliente}/{longitud}/{latitud}", consumes = "application/json", method = RequestMethod.GET)
        public ResponseEntity buscarOrden(@PathVariable("idCliente") String idCliente,@PathVariable("longitud") String longitud,@PathVariable("latitud") String latitud) {

                return ordenService.buscarOrdenDadoLatLng(idCliente,longitud,latitud);
        }

        @RequestMapping(value = "/buscar/{idCliente}", consumes = "application/json", method = RequestMethod.GET)
        public ResponseEntity buscarOrdenesDeUnCliente(@PathVariable("idCliente") String idCliente) {

                return ordenService.buscarOrdenesDeUnCliente(idCliente);
        }
}

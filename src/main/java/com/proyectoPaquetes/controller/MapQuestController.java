package com.proyectoPaquetes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import com.proyectoPaquetes.Service.MapQuestService;


import java.util.ArrayList;
import java.util.List;


@CrossOrigin
@RestController
@RequestMapping(value = "/buscar", produces = "application/json")
public class MapQuestController {


    @Autowired
    private MapQuestService mapquestService;



    @RequestMapping(value = "/coordenadas", method = RequestMethod.GET)
    public ResponseEntity search( @RequestParam("query") String query) {
        query = query.replace(" ", "");
        return mapquestService.buscarLatitudLongitud(query);
    }

    @RequestMapping(value = "/direccion/{lat}/{lng}", method = RequestMethod.GET)
    public ResponseEntity searchDireccion(@PathVariable("lat") String lat, @PathVariable("lng") String lng) {
        return mapquestService.buscarDireccion(lat,lng);
    }


}

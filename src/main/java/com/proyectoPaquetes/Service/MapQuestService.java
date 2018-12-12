package com.proyectoPaquetes.Service;

import com.proyectoPaquetes.command.ClienteLoginCommand;
import com.proyectoPaquetes.command.SignUp.BloqueoSignUpCommand;
import com.proyectoPaquetes.command.SignUp.ClienteSignUpCommand;
import com.proyectoPaquetes.model.MapQuest.Locations;
import com.proyectoPaquetes.model.MapQuest.Results;
import com.proyectoPaquetes.model.MapQuest.LocationsData;
import com.proyectoPaquetes.repository.ClienteRepository;
import com.proyectoPaquetes.response.mapQuest.MapQuestResponse;
import com.proyectoPaquetes.response.mapQuest.LocationResponse;
import com.proyectoPaquetes.response.mapQuest.ResultsResponse;
import com.proyectoPaquetes.response.NotifyResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.proyectoPaquetes.command.ValidarPais;
import com.proyectoPaquetes.command.ClienteUpdateCommand;

import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j

@Service("MapQuestService")
public class MapQuestService {


    private final String CLAVEDELCONSUMIDOR = "zrB5Gm0U8UNpuhKyVz6VBINjzAhmmCAJ";

    private final String SECRETODELCONSUMIDOR = "5UnSkjUiMhFhsjhr";

    public ResponseEntity<Object> buscarLatitudLongitud( String searchTerm,String pais){


        String apiAddressGet = "http://open.mapquestapi.com/geocoding/v1/address?key="+CLAVEDELCONSUMIDOR+"&location="+searchTerm;

        List<LocationsData> location;
        List<Locations> locations;

        RestTemplate restTemplate2 = new RestTemplate();
        Results locationInfo = restTemplate2.getForObject(apiAddressGet, Results.class);
        locations = locationInfo.getResults();

        location = locations.get(0).getLocations();

       String paisDosLetras= "";

       paisDosLetras= retornarPais(pais);


        if (location.isEmpty()) {
            log.info("Search has not been sucessfull");
            return ResponseEntity.badRequest().body(buildNotifyResponse("no_result."));
        }else {
            return ResponseEntity.ok(buildResponseApis(location,paisDosLetras));
        }


    }


    public ResponseEntity<Object> buscarDireccion(String latitud, String longitud){

        String apiAddressGet = "http://open.mapquestapi.com/geocoding/v1/reverse?key="+CLAVEDELCONSUMIDOR+"&location="+latitud+","+longitud+"&includeRoadMetadata=true&includeNearestIntersection=true";



        List<LocationsData> location;
        List<Locations> locations;

        RestTemplate restTemplate2 = new RestTemplate();
        Results locationInfo = restTemplate2.getForObject(apiAddressGet, Results.class);
        locations = locationInfo.getResults();

        location = locations.get(0).getLocations();



        if (location.isEmpty()) {
            log.info("Search has not been sucessfull");
            return ResponseEntity.badRequest().body(buildNotifyResponse("no_result."));
        }else {
            return ResponseEntity.ok(buildResponseApis(location,"No se Encontro el Pais"));
        }


    }




    private MapQuestResponse buildResponseApis(List<LocationsData>  locationData,String pais) {

        List<LocationResponse> mapQuestResponses = new ArrayList<>();
        locationData.forEach( i-> {


                     LocationResponse locationResponse =new LocationResponse();
            if((i.getAdminArea1().equals(pais))||(pais.equals("No se Encontro el Pais"))) {
                locationResponse.setStreet(i.getStreet());

                locationResponse.setAdminArea1(i.getAdminArea1());
                locationResponse.setAdminArea3(i.getAdminArea3());
                locationResponse.setAdminArea4(i.getAdminArea4());
                locationResponse.setAdminArea5(i.getAdminArea5());
                locationResponse.setAdminArea6(i.getAdminArea6());
                locationResponse.setAdminArea1Type(i.getAdminArea1Type());
                locationResponse.setAdminArea3Type(i.getAdminArea3Type());
                locationResponse.setAdminArea4Type(i.getAdminArea4Type());
                locationResponse.setAdminArea5Type(i.getAdminArea5Type());
                locationResponse.setAdminArea6Type(i.getAdminArea6Type());

                locationResponse.setDragPoint(i.getDragPoint());
                locationResponse.setGeocodeQualityCode(i.getGeocodeQualityCode());
                locationResponse.setLat(i.getLatLng().getLat());
                locationResponse.setLng(i.getLatLng().getLng());
                locationResponse.setLinkId(i.getLinkId());
                locationResponse.setUnknownInput(i.getUnknownInput());
                locationResponse.setType(i.getType());
                locationResponse.setSideOfStreet(i.getSideOfStreet());
                locationResponse.setPostalCode(i.getPostalCode());
                locationResponse.setMapUrl(i.getMapUrl());
                locationResponse.setGetGeocodeQuality(i.getGetGeocodeQuality());


                mapQuestResponses.add(locationResponse);
            }


                }
        );
        MapQuestResponse response = new MapQuestResponse();

        response.setLocations(mapQuestResponses);

        return response;

    }



    public String retornarPais(String pais){

        ValidarPais validar = new ValidarPais();

        String arrayPaises[] = validar.getArrayPaises();

        for(int contador = 0; contador < validar.getArrayPaises().length ;contador ++){
            if(arrayPaises[contador].equals(pais)){

                String arrayPaisesDosLetras[] = validar.getArrayPaisesDosLetras();
                return arrayPaisesDosLetras[contador];
            }
      }



        return "No se Encontro el Pais";
    }



        private NotifyResponse buildNotifyResponse(String message) { //MUESTRA UN MENSAJE DE NOTIFICACIÓN
        NotifyResponse respuesta = new NotifyResponse();
        respuesta.setMessage(message);
        respuesta.setTimestamp(LocalDateTime.now());
        return respuesta;
    }

}

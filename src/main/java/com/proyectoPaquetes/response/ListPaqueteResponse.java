package com.proyectoPaquetes.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@ToString
public class ListPaqueteResponse {

    private List<PaqueteResponse> paquetes;

    public List<PaqueteResponse> getPaquetes() {
        return paquetes;
    }

    public void setPaquetes(List<PaqueteResponse> paquetes) {
        this.paquetes = paquetes;
    }
}

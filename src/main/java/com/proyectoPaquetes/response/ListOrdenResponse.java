package com.proyectoPaquetes.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@ToString
public class ListOrdenResponse {

    private List<OrdenResponse> ordenes;

    public List<OrdenResponse> getOrdenes() {
        return ordenes;
    }

    public void setOrdenes(List<OrdenResponse> ordenes) {
        this.ordenes = ordenes;
    }
}

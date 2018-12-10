package com.proyectoPaquetes.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@ToString
public class ListDireccionResponse {

    private List<DireccionResponse> direcciones;

    public List<DireccionResponse> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<DireccionResponse> direcciones) {
        this.direcciones = direcciones;
    }
}

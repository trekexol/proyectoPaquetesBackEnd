package com.proyectoPaquetes.response;


import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class BloqueoResponse {


    private String idCliente;

    private String correoElectronico;

    private String cantidadDeIntentos;

    private String estadoDeBloqueo;

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getCantidadDeIntentos() {
        return cantidadDeIntentos;
    }

    public void setCantidadDeIntentos(String cantidadDeIntentos) {
        this.cantidadDeIntentos = cantidadDeIntentos;
    }

    public String getEstadoDeBloqueo() {
        return estadoDeBloqueo;
    }

    public void setEstadoDeBloqueo(String estadoDeBloqueo) {
        this.estadoDeBloqueo = estadoDeBloqueo;
    }
}

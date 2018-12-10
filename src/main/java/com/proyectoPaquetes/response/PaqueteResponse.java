package com.proyectoPaquetes.response;


import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class PaqueteResponse {

    private String idPaquete;

    private String idOrden;

    private String nombreApellidoEntrega;


    private String descripcionPaquete;


    private String pesoKgs;


    public String getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(String idOrden) {
        this.idOrden = idOrden;
    }

    public String getIdPaquete() {
        return idPaquete;
    }

    public void setIdPaquete(String idPaquete) {
        this.idPaquete = idPaquete;
    }



    public String getNombreApellidoEntrega() {
        return nombreApellidoEntrega;
    }

    public void setNombreApellidoEntrega(String nombreApellidoEntrega) {
        this.nombreApellidoEntrega = nombreApellidoEntrega;
    }

    public String getDescripcionPaquete() {
        return descripcionPaquete;
    }

    public void setDescripcionPaquete(String descripcionPaquete) {
        this.descripcionPaquete = descripcionPaquete;
    }

    public String getPesoKgs() {
        return pesoKgs;
    }

    public void setPesoKgs(String pesoKgs) {
        this.pesoKgs = pesoKgs;
    }
}
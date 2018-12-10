package com.proyectoPaquetes.response;


import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class DireccionResponse {


    private String idDireccion;

    private String idOrden;

    private String idCliente;


    private String direccion1;

    private String direccion2;

    private String codigoPostal;

    private String Ciudad;

    private String pais;

    private String tipoDeDireccion;

    private float longitud;


    private float latitud;


    public String getIdDireccion() {
        return idDireccion;
    }


    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public void setIdDireccion(String idDireccion) {
        this.idDireccion = idDireccion;
    }

    public String getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(String idOrden) {
        this.idOrden = idOrden;
    }

    public String getDireccion1() {
        return direccion1;
    }

    public void setDireccion1(String direccion1) {
        this.direccion1 = direccion1;
    }

    public String getDireccion2() {
        return direccion2;
    }

    public void setDireccion2(String direccion2) {
        this.direccion2 = direccion2;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getCiudad() {
        return Ciudad;
    }

    public void setCiudad(String ciudad) {
        Ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getTipoDeDireccion() {
        return tipoDeDireccion;
    }

    public void setTipoDeDireccion(String tipoDeDireccion) {
        this.tipoDeDireccion = tipoDeDireccion;
    }

    public float getLongitud() {
        return longitud;
    }

    public void setLongitud(float longitud) {
        this.longitud = longitud;
    }

    public float getLatitud() {
        return latitud;
    }

    public void setLatitud(float latitud) {
        this.latitud = latitud;
    }
}

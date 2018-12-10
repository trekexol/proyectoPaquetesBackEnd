package com.proyectoPaquetes.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import com.proyectoPaquetes.command.ValidPassword;


import java.util.Date;

@Entity
@Table(name = "Direccion")
public class Direccion {


    @Id
    private Long idDireccion;


    private Long idOrden;

    private Long idCliente;



    @NotBlank
    @Size(min = 2, max = 40)
    private String direccion1;
    @NotBlank
    @Size(min = 2, max = 40)
    private String direccion2;
    @NotBlank
    @Size(min = 2, max = 40)
    private String codigoPostal;
    @NotBlank
    @Size(min = 2, max = 40)
    private String Ciudad;

    @NotBlank
    @Size(min = 2, max = 40)
    private String pais;

    @NotBlank
    @Size(min = 2, max = 40)
    private String tipoDeDireccion; //si es de recoleccion o entrega


    private float longitud;


    private float latitud;

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Long getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(Long idDireccion) {
        this.idDireccion = idDireccion;
    }

    public Long getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(Long idOrden) {
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

    public String getTipoDeDireccion() {
        return tipoDeDireccion;
    }

    public void setTipoDeDireccion(String tipoDeDireccion) {
        this.tipoDeDireccion = tipoDeDireccion;
    }
}

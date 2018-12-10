package com.proyectoPaquetes.model;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import com.proyectoPaquetes.command.ValidPassword;


import java.util.Date;

@Entity
@Table(name = "Paquete")
public class Paquete extends AuditModel {


    @Id
    private Long idPaquete;

    private Long idOrden;


    @NotBlank
    @Size(min = 2, max = 40)
    private String nombreApellidoEntrega;


    @NotBlank
    @Size(min = 2, max = 40)
    private String descripcionPaquete;


    private double pesoKgs;



    public Long getIdPaquete() {
        return idPaquete;
    }

    public void setIdPaquete(Long idPaquete) {
        this.idPaquete = idPaquete;
    }

    public Long getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(Long idOrden) {
        this.idOrden = idOrden;
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

    public double getPesoKgs() {
        return pesoKgs;
    }

    public void setPesoKgs(double peso) {
        this.pesoKgs = peso;
    }



}
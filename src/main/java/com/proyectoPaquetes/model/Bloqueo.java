package com.proyectoPaquetes.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import com.proyectoPaquetes.command.ValidPassword;


import java.util.Date;

@Entity
@Table(name = "Bloqueo")
public class Bloqueo extends AuditModel {


        @Id
        private Long id;

        private String correoElectronico;


        private int cantidadDeIntentos;

       private boolean estadoDeBloqueo;




    public Long getId() {
        return id;
    }

    public void setId(Long idCliente) {
        this.id = idCliente;
    }

    public int getCantidadDeIntentos() {
        return cantidadDeIntentos;
    }

    public void setCantidadDeIntentos(int cantidadDeIntentos) {
        this.cantidadDeIntentos = cantidadDeIntentos;
    }

    public boolean isEstadoDeBloqueo() {
        return estadoDeBloqueo;
    }

    public void setEstadoDeBloqueo(boolean estadoDeBloqueo) {
        this.estadoDeBloqueo = estadoDeBloqueo;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }
}

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
@Table(name = "Orden")
public class Orden extends AuditModel {


        @Id
        private Long idOrden;

        private Long idCliente;


        @NotBlank
        @Size(min = 2, max = 40)
        private String direccionEntrega;
        @NotBlank
        @Size(min = 2, max = 40)
        private String direccionRecoleccion;


        public Long getIdOrden() {
                return idOrden;
        }

        public void setIdOrden(Long idOrden) {
                this.idOrden = idOrden;
        }

        public Long getIdCliente() {
                return idCliente;
        }

        public void setIdCliente(Long idCliente) {
                this.idCliente = idCliente;
        }


    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public String getDireccionRecoleccion() {
        return direccionRecoleccion;
    }

    public void setDireccionRecoleccion(String direccionRecoleccion) {
        this.direccionRecoleccion = direccionRecoleccion;
    }
}

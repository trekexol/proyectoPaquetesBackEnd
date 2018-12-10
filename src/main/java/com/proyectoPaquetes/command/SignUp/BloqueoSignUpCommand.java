package com.proyectoPaquetes.command.SignUp;

import com.proyectoPaquetes.command.ValidationRules;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

@ToString
@Data
public class BloqueoSignUpCommand {

    private String CorreoElectronico;

    private int cantidadDeIntentos;

   // private boolean estadoDeBloqueo;


    public int getCantidadDeIntentos() {
        return cantidadDeIntentos;
    }

    public void setCantidadDeIntentos(int cantidadDeIntentos) {
        this.cantidadDeIntentos = cantidadDeIntentos;
    }

    public String getCorreoElectronico() {
        return CorreoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        CorreoElectronico = correoElectronico;
    }
/* public boolean isEstadoDeBloqueo() {
        return estadoDeBloqueo;
    }

    public void setEstadoDeBloqueo(boolean estadoDeBloqueo) {
        this.estadoDeBloqueo = estadoDeBloqueo;
    }*/
}

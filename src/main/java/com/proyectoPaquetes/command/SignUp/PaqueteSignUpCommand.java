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
public class PaqueteSignUpCommand implements Serializable {



    @NotNull(message = "Por favor, introduzca su Direccion de Entrega.")
        @NotEmpty(message = "Por favor, introduzca su Direccion de Entrega.")
        @Size(max = ValidationRules.FIRST_LAST_NAME_MAX_SIZE, message = "La Direccion no puede contener más de 40 caracteres.")
        @Pattern(regexp = ValidationRules.FIRST_LAST_NAME_REGEX, message = "La Direccion posee caracteres no válidos.")
        private String nombreApellidoEntrega;

        @NotNull(message = "Por favor, introduzca un Peso.")
       // @NotEmpty(message = "Por favor, introduzca un Peso.")
        private String pesoKgs;

        @NotNull(message = "Por favor, repita la contraseña.")
        @NotEmpty(message = "Por favor, repita la contraseña.")
        @Size(max = ValidationRules.FIRST_LAST_NAME_MAX_SIZE, message = "La Descripcion no puede contener más de 40 caracteres.")
        @Pattern(regexp = ValidationRules.FIRST_LAST_NAME_REGEX, message = "La Descripcion posee caracteres no válidos.")
        private String descripcionPaquete;


    public String getNombreApellidoEntrega() {
        return nombreApellidoEntrega;
    }

    public void setNombreApellidoEntrega(String nombreApellidoEntrega) {
        this.nombreApellidoEntrega = nombreApellidoEntrega;
    }

    public String getPesoKgs() {
        return pesoKgs;
    }

    public void setPesoKgs(String pesoKgs) {
        this.pesoKgs = pesoKgs;
    }

    public String getDescripcionPaquete() {
        return descripcionPaquete;
    }

    public void setDescripcionPaquete(String descripcionPaquete) {
        this.descripcionPaquete = descripcionPaquete;
    }
}

package com.proyectoPaquetes.command.SignUp;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.*;
import java.io.Serializable;

import com.proyectoPaquetes.command.ValidationRules;

@ToString
@Data
public class OrdenSignUpCommand implements Serializable {


        @NotNull(message = "Por favor, introduzca su nombre.")
        @NotEmpty(message = "Por favor, introduzca su nombre.")
        @Pattern(regexp = ValidationRules.FIRST_LAST_NAME_REGEX, message = "El nombre posee caracteres no válidos.")
        @Size(max = ValidationRules.FIRST_LAST_NAME_MAX_SIZE, message = "El nombre no puede contener más de 40 caracteres.")
        private String direccionEntrega;

        @NotNull(message = "Por favor, introduzca su apellido.")
        @NotEmpty(message = "Por favor, introduzca su apellido.")
        @Size(max = ValidationRules.FIRST_LAST_NAME_MAX_SIZE, message = "El apellido no puede contener más de 40 caracteres.")
        @Pattern(regexp = ValidationRules.FIRST_LAST_NAME_REGEX, message = "El apellido posee caracteres no válidos.")
        private String direccionRecoleccion;


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

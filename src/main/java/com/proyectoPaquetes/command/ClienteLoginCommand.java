package com.proyectoPaquetes.command;


import com.proyectoPaquetes.command.ValidationRules;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;


@ToString
@Data
public class ClienteLoginCommand implements Serializable {
    @NotNull(message = "Por favor, introduzca una dirección de email.")
    @NotEmpty(message = "Por favor, introduzca una dirección de email.")
    @Size(min = ValidationRules.EMAIL_MIN_SIZE, message = "La dirección del email debe poseer por lo menos 12 carácteres.")
    @Email(message = "error.format.email")
    private String correoElectronico;

    @NotNull(message = "Por favor, introduzca una contraseña.")
    @NotEmpty(message = "Por favor, introduzca una contraseña.")
    @Size(min = ValidationRules.PASSWORD_MIN_SIZE, message = "La contraseña debe posser por lo menos 8 caracteres.")
    private String contrasena;

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}

package com.proyectoPaquetes.command;


import lombok.Data;
import lombok.ToString;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

import java.util.Date;


@ToString
@Data
public class ClienteDesbloqueo  implements Serializable {

    @NotNull(message = "Por favor, introduzca su fecha de nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    @NotNull(message = "Por favor, introduzca una dirección de correo.")
    @NotEmpty(message = "Por favor, introduzca una dirección de correo.")
    @Size(min = ValidationRules.EMAIL_MIN_SIZE, message = "La dirección de correo debe poseer al menos 12 caracteres.")
    @Email(message = "error.format.email")
    private String correoElectronico;






    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}

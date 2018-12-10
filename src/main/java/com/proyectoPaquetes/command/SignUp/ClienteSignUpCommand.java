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
public class ClienteSignUpCommand implements Serializable {

    //ÉSTOS SON LOS COMANDOS QUE RIGEN LAS VALIDACIONES A LA HORA DE REGISTRAR UN NUEVO USUARIO
    @NotNull(message = "Por favor, introduzca su nombre.")
    @NotEmpty(message = "Por favor, introduzca su nombre.")
    @Pattern(regexp = ValidationRules.FIRST_LAST_NAME_REGEX, message = "El nombre posee caracteres no válidos.")
    @Size(max = ValidationRules.FIRST_LAST_NAME_MAX_SIZE, message = "El nombre no puede contener más de 40 caracteres.")
    private String nombre;

    @NotNull(message = "Por favor, introduzca su apellido.")
    @NotEmpty(message = "Por favor, introduzca su apellido.")
    @Size(max = ValidationRules.FIRST_LAST_NAME_MAX_SIZE, message = "El apellido no puede contener más de 40 caracteres.")
    @Pattern(regexp = ValidationRules.FIRST_LAST_NAME_REGEX, message = "El apellido posee caracteres no válidos.")
    private String apellido;

    @NotNull(message = "Por favor, introduzca una dirección de correo.")
    @NotEmpty(message = "Por favor, introduzca una dirección de correo.")
    @Size(min = ValidationRules.EMAIL_MIN_SIZE, message = "La dirección de correo debe poseer al menos 12 caracteres.")
    @Email(message = "error.format.email")
    private String correoElectronico;

    @NotNull(message = "Por favor, introduzca una contraseña.")
    @NotEmpty(message = "Por favor, introduzca una contraseña.")
    @Size(min = ValidationRules.PASSWORD_MIN_SIZE, message = "La contraseña debe poseer al menos 8 caracteres.")
    private String contrasena;

    @NotNull(message = "Por favor, repita la contraseña.")
    @NotEmpty(message = "Por favor, repita la contraseña.")
    @Size(min = ValidationRules.PASSWORD_MIN_SIZE, message = "La contraseña debe poseer al menos 8 caracteres.")
    private String confirmacioncontrasena;



    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

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

    public String getConfirmacioncontrasena() {
        return confirmacioncontrasena;
    }

    public void setConfirmacioncontrasena(String confirmacioncontrasena) {
        this.confirmacioncontrasena = confirmacioncontrasena;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}

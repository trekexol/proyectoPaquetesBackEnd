package com.proyectoPaquetes.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import com.proyectoPaquetes.command.ValidPassword;


import java.util.Date;

@Entity
@Table(name = "Clientes")
public class Cliente extends AuditModel {


   @Id
    private Long idCliente;

    @NotBlank
    @Size(min = 2, max = 40)
    private String nombre;
    @NotBlank
    @Size(min = 2, max = 40)
    private String apellido;
    @NotBlank
    @Size(min = 2, max = 40)
    private String correoElectronico;
    @NotBlank
    @Size(min = 2, max = 40)
    @ValidPassword
    private String contrasena;

   @Column(name = "fechaNacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    // @Column(columnDefinition = "text")
    //  private String description;


    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

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

   public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
}
}
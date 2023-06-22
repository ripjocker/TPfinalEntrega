package org.example.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class Empleado extends Persona implements Serializable {
    private String username;
    private String password;
    private String confirmPassword;
    private RolEnum rol;

    //Constructores

    public Empleado() {}


    public Empleado(String dni, String nombre, String apellido, String telefono, String email, String username, String password, String confirmPassword, RolEnum rol) {
        super(dni, nombre, apellido, telefono, email);
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.rol = rol;
    }

    //region Getter and Setter

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }


    public RolEnum getRol() {
        return rol;
    }

    public void setRol(RolEnum rol) {
        this.rol = rol;
    }

    //endregion


    @Override
    public String toString() {
        return "Empleado{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", rol=" + rol +
                '}';
    }


}

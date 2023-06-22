package org.example.Models;

import java.util.Objects;

public abstract class Persona {
    private String dni;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;

    //Constructor

    public Persona() {}

    public Persona(String dni, String nombre, String apellido, String telefono, String email) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
    }

    //region Getter and Setter

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //endregion


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return Objects.equals(dni, persona.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }


    @Override
    public String toString() {
        return "[ " +
                "Apellido= " + getApellido() + '\'' +
                "  /// Nombre= '" + getNombre() + '\'' +
                "  /// DNI= '" + getDni() + '\'' +
                "  /// Email= " + getEmail() +'\'' +
                "  /// Telefono= " + getTelefono() +'\''  +
                ']';
    }
}

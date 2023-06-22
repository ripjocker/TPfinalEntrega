package org.example.Models;

import java.io.Serializable;

public class Sucursal implements Serializable {
    private int id;
    private String nombre;
    private String cuit;
    private String telefono;
    private String direccion;

    //Constructores

    public Sucursal() {}

    public Sucursal(int id, String nombre, String cuit, String telefono, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.cuit = cuit;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    //region Setter and Getter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }


    //endregion


    @Override
    public String toString() {
        return "Sucursal{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", cuit='" + cuit + '\'' +
                ", telefono='" + telefono + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}


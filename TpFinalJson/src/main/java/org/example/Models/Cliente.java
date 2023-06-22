package org.example.Models;

import java.io.Serializable;

public class Cliente extends Persona implements Serializable {

    private String Direccion;

    //Constructores


    public Cliente() {}

    public Cliente(String dni, String nombre, String apellido, String telefono, String email, String direccion) {
        super(dni, nombre, apellido, telefono, email);
        Direccion = direccion;
    }

    //Region Getter and Setter


    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }
    public String getDni() {
        return super.getDni();
    }
    //endregion


    @Override
    public String toString() {
        return super.toString()+
                "  /// Direccion= " + getDireccion() +'\'' +
                ']'+ "\n" ;
    }
}

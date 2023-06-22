package org.example.Models;

import java.io.Serializable;
import java.util.Objects;

public class Producto implements Serializable {
    private String codigo;
    private String nombre;
    private String descripcion;
    private int stock;
    private double precioVenta;

    //Constructores

    public Producto() {}

    public Producto(String codigo, String nombre, String descripcion, int stock, double precioVenta) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.stock = stock;
        this.precioVenta = precioVenta;
    }

    //region Getter and Setter

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }


    //endregion


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return Objects.equals(codigo, producto.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public String toString() {
        return "[ Codigo= '" + codigo + '\'' +
                "  /// Nombre= '" + nombre + '\'' +
                "  /// Descripcion= '" + descripcion + '\'' +
                "  /// Stock= " + stock +
                "  /// Precio= " + precioVenta +
                ']';
    }
}

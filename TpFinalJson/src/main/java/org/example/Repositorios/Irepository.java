package org.example.Repositorios;

import java.util.ArrayList;

public interface Irepository<T> {
    void cargar();
    void guardar();
    ArrayList<T> listar();
    void agregar(T...objeto);
    void eliminar(T objeto);
    void modificar(T objeto);
}

package org.example.Repositorios;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.example.Models.Producto;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ProductoRepo implements Irepository<Producto>{
    private final File archivo = new File("C:\\Users\\nico\\Desktop\\LAB 3\\TpFinalJsonultActULTIMO22\\TpFinalJson\\src\\main\\java\\org\\example\\Archivos\\Producto.json");
    private final ObjectMapper mapper = new ObjectMapper();
    private ArrayList<Producto> productos;

    public ProductoRepo() {}

    @Override
    public void cargar() {
        try {
            CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, Producto.class);
            this.productos = mapper.readValue(archivo,collectionType);
        }catch (IOException e){
            this.productos = new ArrayList<>();
        }
    }

    @Override
    public void guardar() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(archivo, this.productos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Producto> listar() {
        cargar();
        return  this.productos;
    }

    @Override
    public void agregar(Producto... producto) {
        cargar();
        ///aslist convierte el cliente para poder agregarlo
        this.productos.addAll(Arrays.asList(producto));
        guardar();
    }

    @Override
    public void eliminar(Producto producto) {
        cargar();
        this.productos.remove(producto);
        System.out.println(productos);
        guardar();
    }

    @Override
    public void modificar(Producto objeto) {

    }
}

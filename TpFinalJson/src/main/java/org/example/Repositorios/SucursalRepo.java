package org.example.Repositorios;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.example.Models.Sucursal;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class SucursalRepo implements Irepository<Sucursal>{
    private final File archivo = new File("C:\\Users\\nico\\Desktop\\LAB 3\\TpFinalJsonultActULTIMO22\\TpFinalJson\\src\\main\\java\\org\\example\\Archivos\\Sucursal.json");
    private final ObjectMapper mapper = new ObjectMapper();
    private ArrayList<Sucursal> sucursales;

    public SucursalRepo() {}

    @Override
    public void cargar() {
        try {
            CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, Sucursal.class);
            this.sucursales = mapper.readValue(archivo,collectionType);
        }catch (IOException e){
            this.sucursales = new ArrayList<>();
        }
    }

    @Override
    public void guardar() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(archivo, this.sucursales);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Sucursal> listar() {
        cargar();
        return  this.sucursales;
    }

    @Override
    public void agregar(Sucursal... sucursal) {
        cargar();
        ///aslist convierte el cliente para poder agregarlo
        this.sucursales.addAll(Arrays.asList(sucursal));
    }

    @Override
    public void eliminar(Sucursal sucursal) {
        cargar();
        this.sucursales.remove(sucursal);
        guardar();
    }

    @Override
    public void modificar(Sucursal sucursal) {

    }
}

package org.example.Repositorios;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.example.Models.Cliente;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ClienteRepo implements Irepository<Cliente>{
    private final File archivo = new File("C:\\Users\\nico\\Desktop\\LAB 3\\TpFinalJsonultActULTIMO22\\TpFinalJson\\src\\main\\java\\org\\example\\Archivos\\Cliente.json");
    private final ObjectMapper mapper = new ObjectMapper();
    private ArrayList<Cliente> clientes;

    public ClienteRepo() {}

    @Override
    public void cargar() {
        try {
            CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(ArrayList.class,Cliente.class);
            this.clientes = mapper.readValue(archivo,collectionType);
        }catch (IOException e){
            this.clientes = new ArrayList<>();
        }
    }

    @Override
    public void guardar() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(archivo, this.clientes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Cliente> listar() {
        cargar();
        return  this.clientes;
    }

    @Override
    public void agregar(Cliente... cliente) {
        cargar();
        ///aslist convierte el cliente para poder agregarlo
        this.clientes.addAll(Arrays.asList(cliente));
        guardar();
    }

    @Override
    public void eliminar(Cliente cliente) {
        cargar();
        this.clientes.remove(cliente);
        guardar();
    }

    @Override
    public void modificar(Cliente cliente) {
        cargar();

        guardar();
    }
}

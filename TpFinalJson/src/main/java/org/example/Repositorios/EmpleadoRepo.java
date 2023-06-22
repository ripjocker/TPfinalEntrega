package org.example.Repositorios;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import jdk.swing.interop.SwingInterOpUtils;
import org.example.Models.Empleado;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.SortedMap;

public class EmpleadoRepo implements Irepository<Empleado> {
    private final File archivo = new File("C:\\Users\\nico\\Desktop\\LAB 3\\TpFinalJsonultActULTIMO22\\TpFinalJson\\src\\main\\java\\org\\example\\Archivos\\Empleado.json");
    private final ObjectMapper mapper = new ObjectMapper();
    private ArrayList<Empleado> empleados;

    public EmpleadoRepo() {
    }

    @Override
    public void cargar() {
        try {
            CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, Empleado.class);
            this.empleados = mapper.readValue(archivo, collectionType);
        } catch (IOException e) {
            this.empleados = new ArrayList<>();
        }
    }


    @Override
    public void guardar() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(archivo, this.empleados);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Empleado> listar() {
        cargar();
        return this.empleados;
    }

    @Override
    public void agregar(Empleado... empleado) {
        cargar();
        ///aslist convierte el cliente para poder agregarlo
        this.empleados.addAll(Arrays.asList(empleado));
        guardar();
    }

    @Override
    public void eliminar(Empleado empleado) {
        cargar();
        this.empleados.remove(empleado);
        guardar();
    }

    @Override
    public void modificar(Empleado empleado) {
        cargar();
        for (Empleado emp : this.empleados) {
            if (emp.getDni().equalsIgnoreCase(empleado.getDni())) {
               if (empleado.getUsername()!=null) {
                   emp.setUsername(empleado.getUsername());
               }
               if(empleado.getEmail()!=null){
                   emp.setEmail(empleado.getEmail());
               }
               if(empleado.getTelefono()!=null){
                  emp.setTelefono(empleado.getTelefono());
               }
                break;
            }
        }
        guardar();
    }
}

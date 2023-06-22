package org.example.Gestion;


import org.example.Models.Sucursal;
import org.example.Repositorios.SucursalRepo;

import java.util.ArrayList;
import java.util.List;

public class GestionSucursal {

    private SucursalRepo sucursalRep = new SucursalRepo();
    public void agregarSucursal(Sucursal sucurs) {
        sucursalRep.agregar(sucurs);
    }

    public void eliminarSucursal(Sucursal sucurs) {
        sucursalRep.eliminar(sucurs);
    }

    public ArrayList<Sucursal> listarSucursales() {
        return sucursalRep.listar();
    }

    public void mostrarDatosSucursal() {
        ArrayList<Sucursal> sucursales = listarSucursales();
        if (sucursales.isEmpty()) {
            System.out.println("No hay sucursales registradas.");
        } else {
            System.out.println("Datos de las sucursales:");
            for (Sucursal sucursal : sucursales) {
                System.out.println("ID: " + sucursal.getId());
                System.out.println("Nombre: " + sucursal.getNombre());
                System.out.println("CUIT: " + sucursal.getCuit());
                System.out.println("Teléfono: " + sucursal.getTelefono());
                System.out.println("Dirección: " + sucursal.getDireccion());
                System.out.println("---------------------------");
            }
        }
    }

    public int obtenerUltimoIdSucursal() {
        SucursalRepo sucursalRepo = new SucursalRepo();
        sucursalRepo.cargar();
        List<Sucursal> sucursales = sucursalRepo.listar();

        if (sucursales.isEmpty()) {
            return 0; // No hay sucursales, retorna ID 0 o algún valor adecuado para indicar que no hay sucursales
        }

        Sucursal ultimaSucursal = sucursales.get(sucursales.size() - 1);
        return ultimaSucursal.getId();
    }
}

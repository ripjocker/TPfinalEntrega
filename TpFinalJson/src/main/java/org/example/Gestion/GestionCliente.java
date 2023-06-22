package org.example.Gestion;

import org.example.Models.Cliente;
import org.example.Repositorios.ClienteRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestionCliente {

    private ClienteRepo clienteRep= new ClienteRepo();

    public GestionCliente() {
    }

    public GestionCliente(ClienteRepo clienteRep) {
        this.clienteRep = clienteRep;
    }

    public void agregarCliente(Cliente cliente) throws Exception {

        List<Cliente> clientes = clienteRep.listar();
        if (clientes == null) {
            clientes = new ArrayList<>();
        }
        for (Cliente c : clientes) {
            if (c.equals(cliente)) {
                throw new Exception("El usuario ya existe");
            }
        }
        clienteRep.agregar(cliente);
    }

    public void eliminarCliente(Cliente client) {
        clienteRep.eliminar(client);
    }

    public ArrayList<Cliente> listarClientes() {
        return clienteRep.listar();
    }

    public void modificarCliente(String dni) {
        Cliente clienteExistente = buscarClientePorDNI(dni);

        if (clienteExistente != null) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Ingrese el nuevo nombre del cliente:");
            String nuevoNombre = scanner.nextLine();

            System.out.println("Ingrese el nuevo apellido del cliente:");
            String nuevoApellido = scanner.nextLine();

            clienteExistente.setNombre(nuevoNombre);
            clienteExistente.setApellido(nuevoApellido);

            clienteRep.guardar();
            System.out.println("Cliente modificado con éxito.");
        } else {
            System.out.println("No se encontró un cliente con ese DNI.");
        }
    }

    private Cliente buscarClientePorDNI(String dni) {
        List<Cliente> clientes = clienteRep.listar();

        for (Cliente cliente : clientes) {
            if (cliente.getDni().equals(dni)) {
                return cliente;
            }
        }

        return null;
    }
}


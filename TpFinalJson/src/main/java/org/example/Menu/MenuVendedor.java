package org.example.Menu;

import org.example.Gestion.GestionCliente;
import org.example.Gestion.GestionProducto;
import org.example.Models.Cliente;
import org.example.Models.Producto;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class MenuVendedor {
    GestionCliente gestionClientes = new GestionCliente();
    GestionProducto gestionProductos = new GestionProducto();
    Scanner scanner = new Scanner(System.in);
    boolean volverMenuPrincipal = true;
    int opcion = 0;

    public void menuVendedor() {

        do {
            System.out.println("/////// MENU VENDEDOR/////");
            System.out.println("//////////////////////////");
            System.out.println("1. Vender producto");
            System.out.println("2. Ver productos");
            System.out.println("3. Agregar cliente");
            System.out.println("4. Ver clientes");
            System.out.println("5. Eliminar cliente");
            System.out.println("6. Salir");
            System.out.println("//////////////////////////");
            System.out.println("//////////////////////////");
            System.out.print("Ingrese una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese los datos del cliente:");
                    System.out.print("DNI del cliente: ");
                    String dniCliente = scanner.nextLine();

                    Cliente cliente = null;
                    List<Cliente> listaClientes = gestionClientes.listarClientes();
                    for (Cliente c : listaClientes) {
                        if (c.getDni().equals(dniCliente)) {
                            cliente = c;
                            break;
                        }
                    }

                    if (cliente != null) {
                        List<Producto> listadoProductos = gestionProductos.venderProducto(cliente);
                        if (listadoProductos != null) {
                            imprimirComandaVenta(cliente, listadoProductos);
                        }
                    } else {
                        System.out.println("Cliente no encontrado.");
                    }

                    volverMenuPrincipal = volverAtras();
                    break;

                case 2:
                    List<Producto> listaProductos = gestionProductos.listarProductos();
                    for (Producto p : listaProductos) {
                        System.out.println(p);
                    }
                    volverMenuPrincipal = volverAtras();
                    break;

                case 3:

                    System.out.println("Ingrese los datos del cliente:");
                    System.out.print("Dni: ");
                    String dni = scanner.nextLine();
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Apellido: ");
                    String apellido = scanner.nextLine();
                    System.out.print("Teléfono: ");
                    String telefono = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Direccion: ");
                    String direcc = scanner.nextLine();

                    Cliente cliente1 = new Cliente(dni, nombre, apellido, telefono, email, direcc);
                    try {
                        gestionClientes.agregarCliente(cliente1);
                        System.out.println("Cliente agregado.");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    volverMenuPrincipal = volverAtras();
                    break;

                case 4:
                    List<Cliente> listaClientes1 = gestionClientes.listarClientes();
                    for (Cliente c : listaClientes1) {
                        System.out.println(c);
                    }
                    volverMenuPrincipal = volverAtras();
                    break;

                case 5:
                    System.out.println("Ingrese el Dni del cliente para eliminar:");
                    System.out.print("Dni: ");
                    String dniEliminar = scanner.nextLine();

                    List<Cliente> clientes = gestionClientes.listarClientes();
                    Cliente clienteEliminar = null;
                    for (Cliente c : clientes) {
                        if (Objects.equals(dniEliminar, c.getDni())) {
                            clienteEliminar = c;
                            break;
                        }
                    }
                    if (clienteEliminar != null) {
                        gestionClientes.eliminarCliente(clienteEliminar);
                        System.out.println("Cliente eliminado exitosamente.");
                    } else {
                        System.out.println("Cliente no encontrado.");
                    }
                    volverMenuPrincipal = volverAtras();
                    break;
                case 6:
                    System.out.println("Chau.");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }

            System.out.println();
        }
        while (opcion != 6);
    }

    private static boolean volverAtras() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.print("Presione Enter para volver al menú principal");
        String opcion = scanner.nextLine();

        return !opcion.equalsIgnoreCase("sdgdfgdffhfdh");
    }

    public void imprimirComandaVenta(Cliente cliente, List<Producto> productos) {
        float totlaVenta = 0;

        for (Producto p : productos) {
            totlaVenta += p.getPrecioVenta() * p.getStock();
        }
        if (productos != null) {
            System.out.println("╔═════════════════════════════════════════╗");
            System.out.println("║             COMANDA DE VENTA            ║");
            System.out.println("╟─────────────────────────────────────────╢");
            System.out.println("║ Cliente: " + cliente.getApellido() + " " + cliente.getNombre());
            System.out.println("║ DNI: " + cliente.getDni());
            System.out.println("║ /////////////////////////////////////// ║");
            System.out.println("║ /////////////////////////////////////// ║");
            System.out.println("║ /////////////////////////////////////// ║");
            for (Producto p : productos) {
                System.out.println("║ Producto: " + p.getNombre() + " (" + p.getDescripcion() + ")");
                System.out.println("║ Código: " + p.getCodigo());
                System.out.println("║ Precio: " + p.getPrecioVenta());
                System.out.println("║ Cantidad: " + p.getStock());
            }
            System.out.println("║ /////////////////////////////////////// ║");
            System.out.println("║ /////////////////////////////////////// ║");
            System.out.println("║ /////////////////////////////////////// ║");
            System.out.println("║ TOTAL : $" + totlaVenta);
            System.out.println("║ /////////////////////////////////////// ║");
            System.out.println("║ ////////Gracias por su compra!///////// ║");
            System.out.println("╚═════════════════════════════════════════╝");
        }
    }


}


package org.example.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.SortedMap;

import org.example.Gestion.GestionCliente;
import org.example.Gestion.GestionEmpleado;
import org.example.Gestion.GestionProducto;
import org.example.Gestion.GestionSucursal;
import org.example.Models.Cliente;
import org.example.Models.Producto;
import org.example.Models.Sucursal;


public class MenuAdmin {

    GestionEmpleado gestionEmpleado = new GestionEmpleado();
    GestionProducto gestionProducto = new GestionProducto();
    GestionSucursal gestionSucursal = new GestionSucursal();
    MenuPrincipal menuPrincipal  = new MenuPrincipal();
    GestionCliente gestionCliente = new GestionCliente();




    public MenuAdmin() {
    }

    public void menuAdmin() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("1. Menu administrador Empleados.");
            System.out.println("2. Menu administrador Productos.");
            System.out.println("3. Menu administrador Clientes.");
            System.out.println("4. Menu administrador Sucursal.");
            System.out.println("5. Volver a iniciar Sesion como Vendedor");
            System.out.println("6. Salir.");
            System.out.print("Ingrese opción del menú: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    menuAdminEmp();
                    break;
                case 2:
                    menuAdminProd();
                    break;
                case 3:
                    menuAdminClient();
                    break;
                case 4:
                    menuAdminSucursal();
                    break;
                case 5:
                    menuPrincipal.iniciarSesion();
                case 6:
                    System.out.println("Saliendo del menú administrador...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, ingrese una opción válida.");
                    break;
            }
        } while (opcion != 5);

        scanner.close();
    }

    public void menuAdminEmp() {
        int opcion = 0;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("//// Menu administrador Empleados ////");
            System.out.println("\t1. Dar de alta un nuevo Empleado.");
            System.out.println("\t2. Generar lista de Empelados.");
            System.out.println("\t3. Modificar los datos de un Empleado.");
            System.out.println("\t4. Dar de baja un Empleado.");
            System.out.println("\t5. Volver al Menu Principal.");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    this.gestionEmpleado.crearEmpleado();
                    break;
                case 2:
                    this.gestionEmpleado.listarEmpleados();
                    break;
                case 3:
                    this.gestionEmpleado.editarDatosEmpleado();
                    break;
                case 4:
                    this.gestionEmpleado.eliminarEmpleado();
                    break;
                case 5:
                    menuAdmin();
                    break;
            }
        } while (opcion != 5);

        scanner.close();
    }

    public void menuAdminProd() {
        int opcion = 0;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("//// Menu administrador Productos ////");
            System.out.println("\t1. Agregar un Producto.");
            System.out.println("\t2. Mostrar listado de Productos.");
            System.out.println("\t3. Modificar un Producto.");
            System.out.println("\t4. Eliminar un Producto.");
            System.out.println("\t5. Volver al menu principal.");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el código del producto: ");
                    String codigo = scanner.nextLine();
                    System.out.print("Ingrese el nombre del producto: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese la descripción del producto: ");
                    String descripcion = scanner.nextLine();
                    System.out.print("Ingrese el stock del producto: ");
                    int stock = scanner.nextInt();
                    System.out.print("Ingrese el precio de venta del producto: ");
                    double precioVenta = scanner.nextDouble();
                    scanner.nextLine(); // Limpiar el buffer del scanner
                    Producto producto = new Producto(codigo, nombre, descripcion, stock, precioVenta);
                    this.gestionProducto.agregarProducto(producto);
                    System.out.println("Producto agregado con éxito.");
                    break;
                case 2:
                    gestionProducto.mostrarListadoProductos();
                    break;
                case 3:
                    System.out.print("Ingrese el código del producto a modificar: ");
                    scanner.nextLine();
                    String codigoModificar = scanner.nextLine();
                    gestionProducto.modificarProducto(codigoModificar);
                    break;
                case 4:
                    System.out.println("Ingrese el código del producto a eliminar: ");
                    scanner.nextLine();
                    String codigoEliminar = scanner.nextLine();

                    ArrayList<Producto> productos = gestionProducto.listarProductos();
                    for (Producto producto1 : productos) {
                        if (producto1.getCodigo().equals(codigoEliminar)) {
                            gestionProducto.eliminarProducto(producto1);
                            System.out.println("Producto eliminado con éxito.");
                            break;
                        }
                    }
                    break;
                case 5:
                    menuAdmin();
                    break;
            }


        }
        while (opcion != 5);
        scanner.close();
    }

    public void menuAdminClient() {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        do {

            System.out.println("//// Menu administrador Clientes ////");
            System.out.println("\t1. Agregar un nuevo Cliente.");
            System.out.println("\t2. Mostrar listado de Clientes.");
            System.out.println("\t3. Modificar un Cliente.");
            System.out.println("\t4. Eliminar un Cliente.");
            System.out.println("\t5. Volver al menu principal.");


            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    agregarCliente(gestionCliente, scanner);
                    break;
                case 2:
                    mostrarListadoClientes(gestionCliente);
                    break;
                case 3:
                    modificarCliente(gestionCliente, scanner);
                    break;
                case 4:
                    eliminarCliente(gestionCliente, scanner);
                    break;
                case 5:
                    menuAdmin();
                    break;
            }


        } while (opcion != 5);
        scanner.close();
    }

    private static void agregarCliente(GestionCliente gestionCliente, Scanner scanner) {
        System.out.println("----- Agregar Cliente -----");
        System.out.print("Ingrese el nombre del cliente: ");
        scanner.nextLine();
        String nombre = scanner.nextLine();

        System.out.print("Ingrese el apellido del cliente: ");

        String apellido = scanner.nextLine();

        System.out.print("Ingrese el DNI del cliente: ");

        String dni = scanner.nextLine();

        System.out.print("Ingrese el teléfono del cliente: ");
        String telefono = scanner.nextLine();

        System.out.print("Ingrese el email del cliente: ");
        String email = scanner.nextLine();

        System.out.print("Ingrese la dirección del cliente: ");

        String direccion = scanner.nextLine();


        Cliente cliente = new Cliente(dni, nombre, apellido, telefono, email, direccion);

        try {
            gestionCliente.agregarCliente(cliente);
            System.out.println("Cliente agregado con éxito.");
        } catch (Exception e) {
            System.out.println("Error al agregar el cliente: " + e.getMessage());
        }
    }

    private static void modificarCliente(GestionCliente gestionCliente, Scanner scanner) {
        System.out.println("----- Modificar Cliente -----");
        System.out.print("Ingrese el DNI del cliente a modificar: ");
        scanner.nextLine();
        String dni = scanner.nextLine();

        gestionCliente.modificarCliente(dni);
    }

    private static void eliminarCliente(GestionCliente gestionCliente, Scanner scanner) {
        System.out.println("----- Eliminar Cliente -----");
        System.out.print("Ingrese el DNI del cliente a eliminar: ");
        scanner.nextLine();
        String dni = scanner.nextLine();

        Cliente cliente = new Cliente();
        cliente.setDni(dni);

        gestionCliente.eliminarCliente(cliente);
        System.out.println("Cliente eliminado con éxito.");
    }

    private static void mostrarListadoClientes(GestionCliente gestionCliente) {
        ArrayList<Cliente> listadoClientes = gestionCliente.listarClientes();
        System.out.println("----- Listado de Clientes -----");
        for (Cliente cliente : listadoClientes) {
            System.out.println("DNI: " + cliente.getDni());
            System.out.println("Nombre: " + cliente.getNombre());
            System.out.println("Apellido :" + cliente.getApellido());
            System.out.println("Telefono:" + cliente.getTelefono());
            System.out.println("Email: " + cliente.getEmail());
            System.out.println("Direccion: " + cliente.getDireccion());
            System.out.println();
        }
    }

    public void menuAdminSucursal(){
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("//// Menu administrador Sucursal////");
            System.out.println("1. Agregar sucursal");
            System.out.println("2. Eliminar sucursal");
            System.out.println("3. Volver al menu principal");
            System.out.print("Ingrese la opción deseada: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    scanner.nextLine(); // Limpiar el buffer de entrada
                    System.out.print("Ingrese el nombre de la sucursal: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese el CUIT de la sucursal: ");
                    String cuit = scanner.nextLine();
                    System.out.print("Ingrese el teléfono de la sucursal: ");
                    String telefono = scanner.nextLine();
                    System.out.print("Ingrese la dirección de la sucursal: ");
                    String direccion = scanner.nextLine();

                    Sucursal sucursal = new Sucursal();
                    sucursal.setNombre(nombre);
                    sucursal.setCuit(cuit);
                    sucursal.setTelefono(telefono);
                    sucursal.setDireccion(direccion);

                    gestionSucursal.agregarSucursal(sucursal);
                    System.out.println("Sucursal agregada correctamente.");
                    break;

                case 2:
                    System.out.print("Ingrese el ID de la sucursal a eliminar: ");
                    int idSucursal = scanner.nextInt();
                    Sucursal sucursalEliminar = buscarSucursalPorId(gestionSucursal, idSucursal);
                    if (sucursalEliminar != null) {
                        gestionSucursal.eliminarSucursal(sucursalEliminar);
                        System.out.println("Sucursal eliminada correctamente.");
                    } else {
                        System.out.println("No se encontró la sucursal con el ID especificado.");
                    }
                    break;

                case 3:
                    menuAdmin();
                    break;

                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                    break;
            }
        } while (opcion != 3);

        scanner.close();
    }

    private static Sucursal buscarSucursalPorId(GestionSucursal gestionSucursal, int idSucursal) {
        for (Sucursal sucursal : gestionSucursal.listarSucursales()) {
            if (sucursal.getId() == idSucursal) {
                return sucursal;
            }
        }
        return null;
    }
}



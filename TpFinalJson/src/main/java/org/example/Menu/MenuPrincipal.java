package org.example.Menu;

import org.example.Gestion.GestionEmpleado;
import org.example.Gestion.GestionSucursal;
import org.example.Menu.MenuVendedor;
import org.example.Models.Empleado;
import org.example.Models.RolEnum;
import org.example.Repositorios.EmpleadoRepo;
import org.example.Repositorios.SucursalRepo;

import java.awt.*;
import java.io.Console;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuPrincipal {

    GestionEmpleado gestionEmpleado = new GestionEmpleado();
    EmpleadoRepo empleadoRepo = new EmpleadoRepo();
    MenuVendedor menuVendedor = new MenuVendedor();
    GestionSucursal gestionSucursal = new GestionSucursal();
    private SucursalRepo sucursalRep = new SucursalRepo();

    public MenuPrincipal() {
    }

    public void iniciarSesion() {

        gestionSucursal.mostrarDatosSucursal();
        empleadoRepo.cargar();
        ArrayList<Empleado> empleados = empleadoRepo.listar();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Menú de Inicio de Sesión ===");
        System.out.println("Roles disponibles: ADMINISTRADOR, EMPLEADO");

        String rol = obtenerRol(scanner);

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Contraseña: ");
        String contrasena = leerContrasena(scanner);

        if (validarCredenciales(rol, email, contrasena, empleados)) {
            System.out.println("Inicio de sesión exitoso.");

            if (rol.equals(RolEnum.ADMINISTRADOR.toString())) {
                MenuAdmin menuAdmin = new MenuAdmin();
                menuAdmin.menuAdmin();
            } else if (rol.equals(RolEnum.EMPLEADO.toString())) {
                MenuVendedor menuVendedor = new MenuVendedor();
                menuVendedor.menuVendedor();
            }
        } else {
            System.out.println("Email o contraseña inválidos.");
            iniciarSesion(); // Volver a solicitar rol, email y contraseña
        }
    }

    private String obtenerRol(Scanner scanner) {
        System.out.print("Rol: ");
        String rol = scanner.nextLine().toUpperCase();

        // Validar que el rol ingresado sea válido
        if (rol.equals(RolEnum.ADMINISTRADOR.toString()) || rol.equals(RolEnum.EMPLEADO.toString())) {
            return rol;
        } else {
            System.out.println("Rol inválido. Por favor, ingrese un rol válido.");
            return obtenerRol(scanner); // Volver a solicitar el rol
        }
    }

    private String leerContrasena(Scanner scanner) {
        // Ocultar la contraseña mientras se escribe
        String contrasena = "";
        Console console = System.console();

        if (console == null) {
            // La consola no admite ocultar la contraseña, se muestra normalmente
            contrasena = scanner.nextLine();
        } else {
            // La consola admite ocultar la contraseña
            char[] contrasenaChars = console.readPassword();
            contrasena = String.valueOf(contrasenaChars);
        }

        return contrasena;
    }

    private boolean validarCredenciales(String rol, String email, String contrasena, ArrayList<Empleado> empleados) {
        for (Empleado empleado : empleados) {
            if (empleado.getRol().toString().equals(rol) &&
                    empleado.getEmail().equals(email) &&
                    empleado.getPassword().equals(contrasena)) {
                return true; // Las credenciales son válidas
            }
        }
        return false; // Las credenciales son inválidas
    }
}
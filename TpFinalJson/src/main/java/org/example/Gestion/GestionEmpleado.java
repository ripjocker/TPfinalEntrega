package org.example.Gestion;

import org.example.Models.Empleado;
import org.example.Models.RolEnum;
import org.example.Repositorios.EmpleadoRepo;

import java.util.ArrayList;
import java.util.Scanner;

public class GestionEmpleado {
    EmpleadoRepo empleadoRepo = new EmpleadoRepo();

    public GestionEmpleado() {
    }

    public GestionEmpleado(EmpleadoRepo empleadoRepo) {
        this.empleadoRepo = empleadoRepo;
    }

    public void traerEmpladosJson() {
        empleadoRepo.cargar();
    }

    public void crearEmpleado() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el DNI del empleado:");
        String dni = scanner.nextLine();

        // Verificar si el DNI ya está registrado
        String finalDni = dni;
        while (empleadoRepo.listar().stream().anyMatch(empleado -> empleado.getDni().equals(finalDni))) {
            System.out.println("El DNI ya está registrado. Ingrese un DNI diferente:");
            dni = scanner.nextLine();
        }

        System.out.println("Ingrese el nombre del empleado:");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese el apellido del empleado:");
        String apellido = scanner.nextLine();

        System.out.println("Ingrese el teléfono del empleado:");
        String telefono = scanner.nextLine();

        System.out.println("Ingrese el email del empleado:");
        String email = scanner.nextLine();

        System.out.println("Ingrese el nombre de usuario del empleado:");
        String username = scanner.nextLine();

        System.out.println("Ingrese la contraseña del empleado:");
        String password = scanner.nextLine();

        System.out.println("Confirme la contraseña del empleado:");
        String confirmPassword = scanner.nextLine();

        System.out.println("Ingrese el rol del empleado ADMINISTRADOR O EMPLEADO:");
        String rol = scanner.nextLine();

        // Crear un objeto Empleado con los datos ingresados
        Empleado empleado = new Empleado(dni, nombre, apellido, telefono, email, username, password, confirmPassword, rolEnumFromString(rol));

        // Agregar el empleado al repositorio
        empleadoRepo.agregar(empleado);
        empleadoRepo.guardar();

        System.out.println("Empleado creado exitosamente.");
    }

    private RolEnum rolEnumFromString(String rol) {
        // Lógica para convertir el string del rol a un objeto de tipo RolEnum

        // Por ejemplo, si los roles posibles son "administrador" y "empleado":
        if (rol.equalsIgnoreCase("administrador")) {
            return RolEnum.ADMINISTRADOR;
        } else if (rol.equalsIgnoreCase("empleado")) {
            return RolEnum.EMPLEADO;
        } else {
            // Si el rol no coincide con ninguna opción, puedes lanzar una excepción o asignar un valor por defecto.
            throw new IllegalArgumentException("Rol inválido");
        }
    }

    public void listarEmpleados() {
        ArrayList<Empleado> listaEmpleados = new ArrayList<>();
        listaEmpleados = this.empleadoRepo.listar();
        System.out.println("/////////////////Listado de Empleados Activos////////////////////");
        System.out.println("/////////////////////////////////////////////////////////////////");
        for (Empleado empleado : listaEmpleados) {
            System.out.println();
            System.out.println("Nombre: " + empleado.getNombre());
            System.out.println("Apellido: " + empleado.getApellido());
            System.out.println("DNI: " + empleado.getDni());
            System.out.println("Telefono: " + empleado.getTelefono());
            System.out.println("email: " + empleado.getEmail());
            System.out.println("Nombre de usuario: " + empleado.getUsername());
            System.out.println("Rol: " + empleado.getRol());
            System.out.println();
        }
    }

    public void editarDatosEmpleado() {

        Empleado empleadoNuevo = new Empleado();
        Scanner scanner = new Scanner(System.in);
        String dniEmpMod;
        String nuevoDato;
        boolean flag = true;
        int opcion = 0;

        System.out.println("Ingrese el numero de documento del usuario a modificar: ");
        dniEmpMod = scanner.nextLine();
        empleadoNuevo.setDni(dniEmpMod);
        do {
            System.out.println("Ingrese el dato que desea modificar: ");
            System.out.println("1.UserName\n2.Telefono\n3.Email\n4. Regresar al menu anterior.");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    do {
                        System.out.print("Ingrese el nuevo nombre de usuario: ");
                        scanner.nextLine();
                        nuevoDato = scanner.nextLine();
                        if (nomUsuarioDisp(nuevoDato)) {
                            System.out.println("Nombre de usuario disponible.");
                            empleadoNuevo.setUsername(nuevoDato);
                            empleadoRepo.modificar(empleadoNuevo);
                            System.out.println("El nombre de usuario se ha modificado correctamente.");
                            flag = false;
                        } else {
                            System.out.println("El nombre de usuario ya se encuentra ocupado.");
                        }
                    } while (flag);
                    break;
                case 2:
                    System.out.print("Ingrese el nuevo numero telefonico: ");
                    scanner.nextLine();
                    nuevoDato = scanner.nextLine();
                    empleadoNuevo.setTelefono(nuevoDato);
                    empleadoRepo.modificar(empleadoNuevo);
                    System.out.println("El numero de telefono se ha modificado correctamente.");
                    break;
                case 3:
                    System.out.print("Ingrese el nuevo email: ");
                    scanner.nextLine();
                    nuevoDato = scanner.nextLine();
                    if (nuevoDato != null) {
                        empleadoNuevo.setEmail(nuevoDato);
                        empleadoRepo.modificar(empleadoNuevo);
                    } else {
                        System.out.println("No se pudo modificar el email. El email ingresado se encuentra vacio.");
                    }
                    break;
            }
        } while (opcion != 4);

    }

    private boolean nomUsuarioDisp(String nuevoDato) {
        ArrayList<Empleado> listaEmpelados = empleadoRepo.listar();
        boolean disponible = true;
        for (Empleado empleado : listaEmpelados) {
            if (empleado.getUsername().equalsIgnoreCase(nuevoDato)) {
                disponible = false;
            }
        }
        return disponible;
    }

    public void eliminarEmpleado() {
        ArrayList<Empleado> listaEmpelados = this.empleadoRepo.listar();
        Scanner scanner = new Scanner(System.in);
        String dniEmpElim;

        System.out.print("Ingrese el dni de la persona que desea eliminar: ");
        dniEmpElim = scanner.nextLine();

        for (Empleado empleado : listaEmpelados) {
            if (empleado.getDni().equalsIgnoreCase(dniEmpElim)) {
                this.empleadoRepo.eliminar(empleado);
                break;
            }
        }
        System.out.println("El numero ingresado no corresponde a ningun Empleado.");
    }

}

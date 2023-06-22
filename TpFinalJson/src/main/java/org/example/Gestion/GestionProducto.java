package org.example.Gestion;

import org.example.Models.Cliente;
import org.example.Models.Producto;
import org.example.Repositorios.ProductoRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestionProducto {
    private ProductoRepo productoRep = new ProductoRepo();

    public GestionProducto() {
    }

    public GestionProducto(ProductoRepo productoRep) {
        this.productoRep = productoRep;
    }

    public void agregarProducto(Producto produc) {
        productoRep.agregar(produc);
    }

    public void eliminarProducto(Producto produc) {
        productoRep.eliminar(produc);
        productoRep.guardar(); // Guardar los cambios en el archivo JSON
    }

    public ArrayList<Producto> listarProductos() {
        return productoRep.listar();
    }

    public void modificarProducto(String codigo) {
        List<Producto> productos = productoRep.listar();

        for (Producto producto : productos) {
            if (producto.getCodigo().equals(codigo)) {
                Scanner scanner = new Scanner(System.in);

                System.out.print("Ingrese el nuevo nombre del producto: ");
                String nuevoNombre = scanner.nextLine();
                System.out.print("Ingrese la nueva descripción del producto: ");
                String nuevaDescripcion = scanner.nextLine();
                System.out.print("Ingrese el nuevo stock del producto: ");
                int nuevoStock = scanner.nextInt();
                System.out.print("Ingrese el nuevo precio de venta del producto: ");
                double nuevoPrecioVenta = scanner.nextDouble();

                producto.setNombre(nuevoNombre);
                producto.setDescripcion(nuevaDescripcion);
                producto.setStock(nuevoStock);
                producto.setPrecioVenta(nuevoPrecioVenta);

                productoRep.guardar();

                System.out.println("Producto modificado con éxito.");
                return;
            }
        }

        System.out.println("No se encontró el producto con el código especificado.");
    }

    public List<Producto> venderProducto(Cliente cliente) {
        List<Producto> productos = productoRep.listar();
        Scanner scanner = new Scanner(System.in);
        List<Producto> productosSeleccionados = new ArrayList<>();
        double totalPagar = 0.0;

        System.out.println("Productos disponibles:");
        for (Producto p : productos) {
            System.out.println("Código: " + p.getCodigo());
            System.out.println("Nombre: " + p.getNombre());
            System.out.println("Stock disponible: " + p.getStock());
            System.out.println("-----------------------------");
        }

        String codigoProducto;
        int cantidad = 0;

        while (true) {
            productos = productoRep.listar();
            System.out.print("Ingrese el código del producto (o '0' para finalizar): ");
            codigoProducto = scanner.nextLine();

            if (codigoProducto.equals("0")) {
                break;
            }

            Producto productoSeleccionado = null;

            for (Producto p : productos) {
                if (p.getCodigo().equals(codigoProducto)) {
                    productoSeleccionado = p;
                    break;
                }
            }

            if (productoSeleccionado == null) {
                System.out.println("El código del producto no es válido.");
                continue;
            }

            if (productoSeleccionado.getStock() <= 0) {
                System.out.println("No hay stock disponible del producto seleccionado.");
                continue;
            }

            System.out.print("Ingrese la cantidad a vender: ");
            cantidad = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer del scanner

            if (cantidad > productoSeleccionado.getStock()) {
                System.out.println("No hay suficiente stock disponible.");
                System.out.println("Stock actual: " + productoSeleccionado.getStock());
                System.out.print("Ingrese una cantidad válida (hasta " + productoSeleccionado.getStock() + "): ");
                try {
                    cantidad = scanner.nextInt();
                } catch (Exception e) {
                    System.out.println("Error dato ingresado.");
                    System.out.print("Ingrese una cantidad válida (hasta " + productoSeleccionado.getStock() + ") ");
                    cantidad = scanner.nextInt();
                }
                scanner.nextLine(); // Limpiar el buffer del scanner
            }

            productoSeleccionado.setStock(productoSeleccionado.getStock() - cantidad);
            productoRep.guardar();
            productoSeleccionado.setStock(cantidad);
            productosSeleccionados.add(productoSeleccionado);
            totalPagar += productoSeleccionado.getPrecioVenta() * cantidad;

            System.out.println("Producto agregado a la venta.");
            System.out.println("-----------------------------");
        }

        System.out.println("Productos seleccionados:");

        for (Producto p : productosSeleccionados) {
            System.out.println("Nombre: " + p.getNombre());
            System.out.println("Cantidad vendida: " + (p.getStock()));
            System.out.println("-----------------------------");
        }

        System.out.println("Total a pagar: " + totalPagar);
        return productosSeleccionados;
    }

    public void mostrarListadoProductos() {
        List<Producto> listadoProductos = listarProductos();
        for (Producto prod: listadoProductos){
            System.out.println("Codigo: " + prod.getCodigo());
            System.out.println("Nombre: " + prod.getNombre());
            System.out.println("Descripcion: " + prod.getDescripcion());
            System.out.println("Stock: " + prod.getStock());
            System.out.println("Precio de venta: $" + prod.getPrecioVenta());
            System.out.println();
        }
    }
}



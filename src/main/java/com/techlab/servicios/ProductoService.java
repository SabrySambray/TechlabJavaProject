package com.techlab.servicios;

import com.techlab.productos.*;
import java.util.*;

public class ProductoService {
    private List<Producto> productos = new ArrayList<>();


    public void agregarProducto(Scanner scanner) {
        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine();

        double precio = 0;
        boolean precioValido = false;
        while (!precioValido) {
            System.out.print("Precio: ");
            String inputPrecio = scanner.nextLine();
            try {
                precio = Double.parseDouble(inputPrecio);
                precioValido = true;
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor ingrese un número válido para el precio.");
            }
        }

        int stock = 0;
        boolean stockValido = false;
        while (!stockValido) {
            System.out.print("Stock: ");
            String inputStock = scanner.nextLine();

            // Verifica que el input sea un número entero (opcionalmente con signo)
            if (inputStock.matches("\\d+")) {  // "-?" permite signo negativo, "\\d+" solo dígitos
                try {
                    stock = Integer.parseInt(inputStock);
                    stockValido = true;
                } catch (NumberFormatException e) {
                    System.out.println("Error: El número ingresado está fuera del rango permitido para un entero.");
                }
            } else {
                System.out.println("Error: Debe ingresar un número entero (sin decimales).");
            }
        }

        productos.add(new Producto(nombre, precio, stock));
        System.out.println("Producto agregado exitosamente.");
    }


    public void agregarProductosEjemplo() {
        productos.add(new Producto("Laptop Lenovo ThinkPad", 1500.0, 10));
        productos.add(new Producto("Smartphone Samsung Galaxy S22", 999.99, 15));
        productos.add(new Producto("Monitor LG Ultrawide 29\"", 299.99, 8));
        productos.add(new Producto("Teclado Mecánico Redragon", 89.50, 20));
        productos.add(new Producto("Mouse Logitech G502", 59.99, 25));
        productos.add(new Producto("Disco SSD Kingston 1TB", 119.0, 30));
        productos.add(new Producto("Memoria RAM Corsair 16GB DDR4", 75.0, 18));
        productos.add(new Producto("Auriculares Sony WH-1000XM4", 349.99, 5));
        productos.add(new Producto("Impresora HP Deskjet", 99.95, 12));
        productos.add(new Producto("Router TP-Link AX1800", 64.99, 22));
        productos.add(new Producto("Tablet iPad 9ª Gen", 429.0, 9));
        productos.add(new Producto("Webcam Logitech C920", 109.0, 14));
        productos.add(new Producto("Base Refrigerante para Laptop", 24.50, 30));
        productos.add(new Producto("Cámara de seguridad WiFi", 59.90, 17));
        productos.add(new Producto("Cargador portátil 20000mAh", 39.99, 40));
        productos.add(new Producto("Cable HDMI 2.1 2m", 12.0, 50));
        productos.add(new Producto("Hub USB 3.0 de 4 puertos", 15.5, 35));
        productos.add(new Producto("Soporte de Monitor de Escritorio", 44.0, 10));
        productos.add(new Producto("Proyector portátil Anker Nebula", 279.99, 6));
        productos.add(new Producto("Micrófono Blue Yeti", 129.0, 7));
        System.out.println("✔ Productos tecnológicos de ejemplo agregados correctamente.");
    }

    public void listarProductos() {
        if (productos.isEmpty()) System.out.println("No hay productos registrados.");
        else productos.forEach(System.out::println);
    }

    public Producto buscarPorId(int id) {
        return productos.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    public void buscarActualizarProducto(Scanner scanner) {
        System.out.print("Ingrese ID de producto a buscar: "); int id = scanner.nextInt();
        Producto p = buscarPorId(id);
        if (p != null) {
            System.out.println(p);
            System.out.print("¿Desea actualizar precio? (s/n): ");
            if (scanner.next().equalsIgnoreCase("s")) {
                System.out.print("Nuevo precio: ");
                p.setPrecio(scanner.nextDouble());
            }
            System.out.print("¿Desea actualizar stock? (s/n): ");
            if (scanner.next().equalsIgnoreCase("s")) {
                System.out.print("Nuevo stock: ");
                p.setStock(scanner.nextInt());
            }
        } else System.out.println("Producto no encontrado.");
    }

    public void eliminarProducto(Scanner scanner) {
        System.out.print("Ingrese ID del producto a eliminar: ");
        int id = scanner.nextInt();
        Producto p = buscarPorId(id);
        if (p != null) {
            productos.remove(p);
            System.out.println("Producto eliminado.");
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    public List<Producto> getProductos() {
        return productos;
    }
}
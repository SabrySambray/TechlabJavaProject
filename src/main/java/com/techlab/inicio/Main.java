package com.techlab.inicio;
import com.techlab.productos.*;
import com.techlab.pedidos.*;
import com.techlab.servicios.*;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        ProductoService productoService = new ProductoService();
        PedidoService pedidoService = new PedidoService(productoService);
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n============= SISTEMA DE GESTIÓN - TECHLAB =============");
            System.out.println("1) Agregar producto");
            System.out.println("2) Listar productos");
            System.out.println("3) Buscar/Actualizar producto");
            System.out.println("4) Eliminar producto");
            System.out.println("5) Crear un pedido");
            System.out.println("6) Listar pedidos");
            System.out.println("7) Salir");
            System.out.println("8) Agregar productos de ejemplo (recomendable para testear)");
            System.out.print("Elija una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1 -> productoService.agregarProducto(scanner);
                case 2 -> productoService.listarProductos();
                case 3 -> productoService.buscarActualizarProducto(scanner);
                case 4 -> productoService.eliminarProducto(scanner);
                case 5 -> pedidoService.crearPedido(scanner);
                case 6 -> pedidoService.listarPedidos();
                case 7 -> System.out.println("Saliendo del sistema. ¡Hasta pronto!");
                case 8 -> productoService.agregarProductosEjemplo();
                default -> System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 7);

        scanner.close();
    }
}
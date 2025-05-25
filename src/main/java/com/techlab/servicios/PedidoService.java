package com.techlab.servicios;

import com.techlab.pedidos.*;
import com.techlab.productos.*;
import com.techlab.excepciones.*;
import java.util.*;

public class PedidoService {
    private List<Pedido> pedidos = new ArrayList<>();
    private ProductoService productoService;

    public PedidoService(ProductoService productoService) {
        this.productoService = productoService;
    }

    /*
    public void crearPedido(Scanner scanner) {
        Pedido pedido = new Pedido();
        while (true) {
            productoService.listarProductos();
            System.out.print("Ingrese ID del producto (-1 para finalizar): ");
            int id = scanner.nextInt();
            if (id == -1) break;
            Producto producto = productoService.buscarPorId(id);
            if (producto == null) {
                System.out.println("Producto no encontrado.");
                continue;
            }
            System.out.print("Cantidad: ");
            int cantidad = scanner.nextInt();
            if (cantidad > producto.getStock()) {
                System.out.println("Stock insuficiente.");
                continue;
            }
            producto.setStock(producto.getStock() - cantidad);
            pedido.agregarLinea(new LineaPedido(producto, cantidad));
        }
        if (!pedido.getLineas().isEmpty()) {
            pedidos.add(pedido);
            System.out.println("Pedido creado con éxito:");
            System.out.println(pedido);
        } else {
            System.out.println("No se creó el pedido (vacío).");
        }
    }*/

    public void crearPedido(Scanner scanner) {
        Pedido pedido = new Pedido();
        while (true) {
            try {
                productoService.listarProductos();
                System.out.print("Ingrese ID del producto (-1 para finalizar): ");
                int id = scanner.nextInt();
                if (id == -1) break;

                Producto producto = productoService.buscarPorId(id);
                if (producto == null) {
                    System.out.println("Producto no encontrado.");
                    continue;
                }

                System.out.print("Cantidad: ");
                int cantidad = scanner.nextInt();

                // Verificar stock y lanzar excepción si es necesario
                if (cantidad > producto.getStock()) {
                    throw new StockInsuficienteException(
                            "Stock insuficiente para el producto " + producto.getNombre() +
                                    ". Stock disponible: " + producto.getStock());
                }

                producto.setStock(producto.getStock() - cantidad);
                pedido.agregarLinea(new LineaPedido(producto, cantidad));

            } catch (StockInsuficienteException e) {
                System.out.println(e.getMessage());
                continue;
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
                scanner.nextLine(); // Limpiar buffer
                continue;
            }
        }

        if (!pedido.getLineas().isEmpty()) {
            pedidos.add(pedido);
            System.out.println("Pedido creado con éxito:");
            System.out.println(pedido);
        } else {
            System.out.println("No se creó el pedido (vacío).");
        }
    }

    public void listarPedidos() {
        if (pedidos.isEmpty()) System.out.println("No hay pedidos.");
        else pedidos.forEach(System.out::println);
    }
}
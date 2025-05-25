package com.techlab.pedidos;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private static int contador = 0;
    private int id;
    private List<LineaPedido> lineas;

    public Pedido() {
        this.id = ++contador;
        this.lineas = new ArrayList<>();
    }

    public void agregarLinea(LineaPedido linea) {
        lineas.add(linea);
    }

    public double calcularTotal() {
        return lineas.stream().mapToDouble(LineaPedido::getSubtotal).sum();
    }

    public List<LineaPedido> getLineas() { return lineas; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Pedido ID: " + id + "\n");
        for (LineaPedido l : lineas) {
            sb.append(l).append("\n");
        }
        sb.append("Total: ").append(String.format("%.2f", calcularTotal())).append("\n");
        return sb.toString();
    }
}
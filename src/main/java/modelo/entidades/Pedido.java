package modelo.entidades;

import java.time.LocalDateTime;
import java.util.List;

public class Pedido {

    private LocalDateTime fecha;
    private double total;
    private List<ItemCarrito> productos;

    public Pedido() {
    }

    public Pedido(LocalDateTime fecha, double total, List<ItemCarrito> productos) {
        this.fecha = fecha;
        this.total = total;
        this.productos = productos;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<ItemCarrito> getProductos() {
        return productos;
    }

    public void setProductos(List<ItemCarrito> productos) {
        this.productos = productos;
    }
}

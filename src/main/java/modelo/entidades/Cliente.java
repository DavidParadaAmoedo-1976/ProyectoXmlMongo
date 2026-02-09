package modelo.entidades;

import java.util.List;

public class Cliente {

    private String id;
    private String nombre;
    private String email;
    private List<ItemCarrito> carrito;
    private List<Pedido> pedidos;

    public Cliente() {
    }

    public Cliente(String id, String nombre, String email,
                   List<ItemCarrito> carrito,
                   List<Pedido> pedidos) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.carrito = carrito;
        this.pedidos = pedidos;
    }

    /* GETTERS Y SETTERS */

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<ItemCarrito> getCarrito() {
        return carrito;
    }

    public void setCarrito(List<ItemCarrito> carrito) {
        this.carrito = carrito;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}


package modelo.entidades;

public class Producto {

    private String id;
    private String nombre;
    private double precio;
    private int disponibilidad;
    private String categoria;
    private String fabricante;
    private String descripcion;

    public Producto() {
    }

    public Producto(String id, String nombre, double precio,
                    int disponibilidad, String categoria,
                    String fabricante, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.disponibilidad = disponibilidad;
        this.categoria = categoria;
        this.fabricante = fabricante;
        this.descripcion = descripcion;
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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(int disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

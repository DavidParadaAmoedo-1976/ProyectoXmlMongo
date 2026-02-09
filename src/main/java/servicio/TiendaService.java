package servicio;

import dao.mongo.TiendaMongoDAO;
import modelo.entidades.Cliente;
import modelo.entidades.ItemCarrito;
import modelo.entidades.Pedido;
import org.bson.Document;
import utilidades.MongoMapper;

import java.time.LocalDateTime;
import java.util.List;

public class TiendaService {

    private final TiendaMongoDAO dao;
    private Cliente clienteActual;

    public TiendaService() {
        this.dao = new TiendaMongoDAO();
        this.clienteActual = null;
    }

    /* ======================
       CLIENTES
       ====================== */

    public boolean crearCliente(String nombre, String email) {
        if (dao.buscarClientePorEmail(email) != null) {
            return false; // email repetido
        }
        dao.crearCliente(nombre, email);
        return true;
    }

    public boolean identificarCliente(String email) {
        Document doc = dao.buscarClientePorEmail(email);
        if (doc == null) return false;

        this.clienteActual = MongoMapper.documentToCliente(doc);
        return true;
    }

    public void borrarClienteActual() {
        comprobarCliente();
        dao.borrarCliente(clienteActual.getId());
        clienteActual = null;
    }

    public void modificarCliente(String campo, Object valor) {
        comprobarCliente();
        dao.modificarCampo(clienteActual.getId(), campo, valor);
    }

    public Cliente getClienteActual() {
        return clienteActual;
    }

    /* ======================
       CARRITO
       ====================== */

    public void añadirProductoCarrito(String idProducto, double precio, int cantidad) {
        comprobarCliente();

        ItemCarrito item = new ItemCarrito(idProducto, precio, cantidad);
        Document doc = MongoMapper.itemToDocument(item);

        dao.añadirProductoCarrito(clienteActual.getId(), doc);
    }

    public List<ItemCarrito> obtenerCarrito() {
        comprobarCliente();

        List<Document> docs = dao.obtenerCarrito(clienteActual.getId());
        return MongoMapper.documentToItems(docs);
    }

    public double calcularTotalCarrito() {
        return obtenerCarrito().stream()
                .mapToDouble(ItemCarrito::getSubtotal)
                .sum();
    }

    /* ======================
       PEDIDOS
       ====================== */

    public List<Pedido> obtenerPedidos() {
        comprobarCliente();

        List<Document> docs = dao.obtenerPedidos(clienteActual.getId());
        return MongoMapper.documentToPedidos(docs);
    }

    public void pagarCarrito() {
        comprobarCliente();

        List<ItemCarrito> carrito = obtenerCarrito();
        if (carrito.isEmpty()) {
            throw new IllegalStateException("El carrito está vacío");
        }

        double total = calcularTotalCarrito();
        Pedido pedido = new Pedido(LocalDateTime.now(), total, carrito);

        Document pedidoDoc = MongoMapper.pedidoToDocument(pedido);
        dao.crearPedido(clienteActual.getId(), pedidoDoc);
        dao.vaciarCarrito(clienteActual.getId());
    }

    /* ======================
       CONSULTAS MONGO
       ====================== */

    public void consultaTotalCarritos() {
        dao.totalCarritosOrdenados()
                .forEachRemaining(d ->
                        System.out.println(d.getString("email") +
                                " -> " + d.getDouble("total")));
    }

    public void consultaTotalGastadoClientes() {
        dao.totalGastadoPorCliente()
                .forEachRemaining(d ->
                        System.out.println(d.getString("email") +
                                " -> " + d.getDouble("totalGastado")));
    }

    /* ======================
       UTILIDAD
       ====================== */

    private void comprobarCliente() {
        if (clienteActual == null) {
            throw new IllegalStateException("No hay cliente identificado");
        }
    }
}


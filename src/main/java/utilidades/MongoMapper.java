package utilidades;

import modelo.entidades.Cliente;
import modelo.entidades.ItemCarrito;
import modelo.entidades.Pedido;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MongoMapper {

    /* ======================
       DOCUMENT -> ENTIDADES
       ====================== */

    public static Cliente documentToCliente(Document doc) {
        if (doc == null) return null;

        Cliente cliente = new Cliente();
        cliente.setId(doc.getObjectId("_id").toHexString());
        cliente.setNombre(doc.getString("nombre"));
        cliente.setEmail(doc.getString("email"));
        cliente.setCarrito(documentToItems(doc.getList("carrito", Document.class)));
        cliente.setPedidos(documentToPedidos(doc.getList("pedidos", Document.class)));

        return cliente;
    }

    public static List<ItemCarrito> documentToItems(List<Document> docs) {
        List<ItemCarrito> items = new ArrayList<>();
        if (docs == null) return items;

        for (Document d : docs) {
            ItemCarrito item = new ItemCarrito(
                    d.getString("idProducto"),
                    d.getDouble("precioUnitario"),
                    d.getInteger("cantidad")
            );
            items.add(item);
        }
        return items;
    }

    public static List<Pedido> documentToPedidos(List<Document> docs) {
        List<Pedido> pedidos = new ArrayList<>();
        if (docs == null) return pedidos;

        for (Document d : docs) {
            Pedido pedido = new Pedido();
            pedido.setFecha(dateToLocalDateTime(d.getDate("fecha")));
            pedido.setTotal(d.getDouble("total"));
            pedido.setProductos(documentToItems(d.getList("productos", Document.class)));
            pedidos.add(pedido);
        }
        return pedidos;
    }

    /* ======================
       ENTIDADES -> DOCUMENT
       ====================== */

    public static Document clienteToDocument(String nombre, String email) {
        return new Document("nombre", nombre)
                .append("email", email)
                .append("carrito", new ArrayList<>())
                .append("pedidos", new ArrayList<>());
    }

    public static Document itemToDocument(ItemCarrito item) {
        return new Document("idProducto", item.getIdProducto())
                .append("precioUnitario", item.getPrecioUnitario())
                .append("cantidad", item.getCantidad());
    }

    public static Document pedidoToDocument(Pedido pedido) {
        return new Document("fecha", localDateTimeToDate(pedido.getFecha()))
                .append("total", pedido.getTotal())
                .append("productos", itemsToDocuments(pedido.getProductos()));
    }

    public static List<Document> itemsToDocuments(List<ItemCarrito> items) {
        List<Document> docs = new ArrayList<>();
        for (ItemCarrito item : items) {
            docs.add(itemToDocument(item));
        }
        return docs;
    }

    /* ======================
       FECHAS
       ====================== */

    private static LocalDateTime dateToLocalDateTime(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    private static Date localDateTimeToDate(LocalDateTime ldt) {
        return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }

    /* ======================
       UTILIDAD EXTRA
       ====================== */

    public static ObjectId stringToObjectId(String id) {
        return new ObjectId(id);
    }
}


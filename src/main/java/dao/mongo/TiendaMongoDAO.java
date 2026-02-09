package dao.mongo;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.List;

public class TiendaMongoDAO {

    private final MongoCollection<Document> coleccion;

    public TiendaMongoDAO() {
        MongoDatabase db = ConexionMongo.getDatabase();
        this.coleccion = db.getCollection("tienda");
    }

    /* ======================
       CLIENTES
       ====================== */

    public void crearCliente(String nombre, String email) {
        Document cliente = new Document("nombre", nombre)
                .append("email", email)
                .append("carrito", List.of())
                .append("pedidos", List.of());

        coleccion.insertOne(cliente);
    }

    public Document buscarClientePorEmail(String email) {
        return coleccion.find(Filters.eq("email", email)).first();
    }

    public void borrarCliente(String idCliente) {
        coleccion.deleteOne(Filters.eq("_id", new ObjectId(idCliente)));
    }

    public void modificarCampo(String idCliente, String campo, Object valor) {
        coleccion.updateOne(
                Filters.eq("_id", new ObjectId(idCliente)),
                Updates.set(campo, valor)
        );
    }

    /* ======================
       CARRITO
       ====================== */

    public void añadirProductoCarrito(String idCliente, Document productoCarrito) {
        coleccion.updateOne(
                Filters.eq("_id", new ObjectId(idCliente)),
                Updates.push("carrito", productoCarrito)
        );
    }

    public List<Document> obtenerCarrito(String idCliente) {
        Document cliente = coleccion.find(
                Filters.eq("_id", new ObjectId(idCliente))
        ).first();

        return cliente.getList("carrito", Document.class);
    }

    public void vaciarCarrito(String idCliente) {
        coleccion.updateOne(
                Filters.eq("_id", new ObjectId(idCliente)),
                Updates.set("carrito", List.of())
        );
    }

    /* ======================
       PEDIDOS
       ====================== */

    public void crearPedido(String idCliente, Document pedido) {
        coleccion.updateOne(
                Filters.eq("_id", new ObjectId(idCliente)),
                Updates.push("pedidos", pedido)
        );
    }

    public List<Document> obtenerPedidos(String idCliente) {
        Document cliente = coleccion.find(
                Filters.eq("_id", new ObjectId(idCliente))
        ).first();

        return cliente.getList("pedidos", Document.class);
    }

    /* ======================
       CONSULTAS AGREGADAS
       ====================== */

    // Consulta 16
    public MongoCursor<Document> totalCarritosOrdenados() {
        return coleccion.aggregate(List.of(
                Aggregates.project(
                        Projections.fields(
                                Projections.include("email"),
                                Projections.computed(
                                        "total",
                                        new Document("$sum", "$carrito.precio_unitario")
                                )
                        )
                ),
                Aggregates.sort(Sorts.ascending("total"))
        )).iterator();
    }

    // Consulta 17
    public MongoCursor<Document> totalGastadoPorCliente() {
        return coleccion.aggregate(List.of(
                Aggregates.project(
                        Projections.fields(
                                Projections.include("email"),
                                Projections.computed(
                                        "totalGastado",
                                        new Document("$sum", "$pedidos.total")
                                )
                        )
                )
        )).iterator();
    }
}

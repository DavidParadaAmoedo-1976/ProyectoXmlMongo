package dao.mongo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class ConexionMongo {

    private static final String URI = "mongodb://localhost:27017";
    private static final String DB_NAME = "tienda";

    private static MongoClient client;
    private static MongoDatabase database;

    private ConexionMongo() { }

    public static MongoDatabase getDatabase() {
        if (database == null) {
            client = MongoClients.create(URI);
            database = client.getDatabase(DB_NAME);
        }
        return database;
    }

    public static void cerrar() {
        if (client != null) {
            client.close();
        }
    }
}


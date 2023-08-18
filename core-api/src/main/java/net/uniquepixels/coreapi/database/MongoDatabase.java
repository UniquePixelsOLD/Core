package net.uniquepixels.coreapi.database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;

public class MongoDatabase {

    private final MongoClient client;
    private final com.mongodb.client.MongoDatabase database;

    public MongoDatabase(String connectionString) {

        this.client = MongoClients.create(connectionString);
        this.database = this.client.getDatabase("uniquepixels");
    }

    public <C> MongoCollection<C> collection(String collectionName, Class<C> documentClass) {
        return this.database.getCollection(collectionName, documentClass);
    }

    public void close() {
        this.client.close();
    }
}

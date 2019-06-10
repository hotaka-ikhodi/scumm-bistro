package com.scumm.bdd;


import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class MongoConnection {

    private static MongoConnection mongoConnectionInstance;

    private MongoClient mongoClient;

    private MongoConnection() {
        String uri_string = System.getenv("MRNOOW_MONGODB_URI")!=null? System.getenv("MRNOOW_MONGODB_URI") : "mongodb://localhost:27017";
        MongoClientURI uri = new MongoClientURI(uri_string);
        mongoClient = new MongoClient(uri);
    }

    public static synchronized MongoConnection getInstance() {
        if (mongoConnectionInstance == null) {
            mongoConnectionInstance = new MongoConnection();
        }
        return mongoConnectionInstance;
    }

    public MongoClient getConnection() {
        return mongoClient;
    }
}

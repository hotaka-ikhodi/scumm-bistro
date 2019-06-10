package com.scumm.core;
import org.springframework.data.mongodb.repository.MongoRepository;


public abstract class MongoEntity implements MongoRepository {
    private String collectionName;

    public MongoEntity() {

    }

    public MongoEntity(String collectionName) {
        setCollectionName(collectionName);
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }
}

package com.scumm.core.domain.entities;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.repository.MongoRepository;

public class Entity<T extends MongoRepository> {

    public void setRepository(T repository) {
        this.repository = repository;
    }

    @Transient
    private T repository;

    @Id
    protected ObjectId id;

    public Entity() {
        id = ObjectId.get();
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public void save(){
        repository.save(this);
    }
}

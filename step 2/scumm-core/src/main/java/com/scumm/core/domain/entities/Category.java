package com.scumm.core.domain.entities;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Category {

    @Id
    private ObjectId id;
    private String name;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

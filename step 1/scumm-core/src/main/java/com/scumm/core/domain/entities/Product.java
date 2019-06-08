package com.scumm.core.domain.entities;

import com.scumm.core.domain.repositories.ProductRepository;
import org.bson.types.ObjectId;

public class Product extends Entity<ProductRepository> {

    private int priority;
    private int dinners;
    private ObjectId categoryId;
    private String name;
    private String description;
    private ObjectId companyId;

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getDinners() {
        return dinners;
    }

    public void setDinners(int dinners) {
        this.dinners = dinners;
    }

    public ObjectId getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(ObjectId categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ObjectId getCompanyId() {
        return companyId;
    }

    public void setCompanyId(ObjectId companyId) {
        this.companyId = companyId;
    }
}

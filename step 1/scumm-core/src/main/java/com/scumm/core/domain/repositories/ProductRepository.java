package com.scumm.core.domain.repositories;

import com.scumm.core.domain.entities.Product;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, ObjectId> {
}

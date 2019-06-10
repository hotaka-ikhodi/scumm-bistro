package com.scumm.core.domain.factories;

import com.scumm.core.domain.entities.Entity;
import com.scumm.core.microservices.MicroBean;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;


public abstract class EntityFactory<TEntity extends Entity, TRepo extends MongoRepository<TEntity, ObjectId>> {

    private static final Logger logger = LoggerFactory.getLogger(MicroBean.class);

    @Autowired
    private ApplicationContext context;

    @Autowired
    private TRepo repository;

    public TEntity createNew(Class<TEntity> classOf) throws EntityCreationException {
        Constructor<TEntity> constructor = null;
        try {
            constructor = classOf.getConstructor();
            TEntity entity = constructor.newInstance();
            entity.setRepository(repository);
            return entity;
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            logger.error("Error al crear la instancia", e);
            throw new EntityCreationException(e);
        }
    }

    public TEntity getById(ObjectId objectId){
        Optional<TEntity> optional = repository.findById(objectId);
        if (optional.isPresent()) {
            TEntity entity = optional.get();
            entity.setRepository(repository);
            return entity;
        }
        else {
            return null;
        }
    }
}

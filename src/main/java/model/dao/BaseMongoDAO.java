package model.dao;

import com.google.code.morphia.Datastore;
import org.bson.types.ObjectId;

import java.util.List;

/**
 *
 */
public abstract class BaseMongoDAO<Entity> implements GenericDAO<Entity, ObjectId> {
    private Datastore datastore;
    private final Class<Entity> entityType;

    protected final Datastore getDatastore() {
        return datastore;
    }

    @Override
    public Entity find(ObjectId id) {
        return (Entity)null;
    }

    @Override
    public List<Entity> findAll() {
        return datastore.find(entityType).asList();
    }

    @Override
    public void insert(Entity entity) {
        datastore.save(entity);
    }

    public BaseMongoDAO(Class<Entity> klass, Datastore datastore) {
        this.entityType = klass;
        this.datastore = datastore;
    }
}

package model.dao;

import java.io.Serializable;
import java.util.List;

/**
 *
 */
public interface GenericDAO<Entity, Id extends Serializable> {
    List<Entity> findAll();

    Object find(Id id);

    void insert(Entity entity);
}

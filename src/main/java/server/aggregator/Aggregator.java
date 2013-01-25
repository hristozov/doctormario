package server.aggregator;

import model.dao.GenericDAO;

/**
 */
public class Aggregator<T> {
    private GenericDAO dao;

    public Aggregator(GenericDAO<T, ?> dao) {
        this.dao = dao;
    }

    public void insertRecord(T record) {
        dao.insert(record);
    }
}

package server.providers;

import model.dao.ClinicalCaseDAO;
import model.dao.GenericDAO;

/**
 * Created with IntelliJ IDEA.
 * User: gh
 * Date: 1/25/13
 * Time: 11:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class DaoProviderSingleton {
    private static DaoProviderSingleton ourInstance = new DaoProviderSingleton();

    private GenericDAO dao;

    public GenericDAO getDao() {
        return dao;
    }

    public static DaoProviderSingleton getInstance() {
        return ourInstance;
    }

    private DaoProviderSingleton() {
        this.dao = new ClinicalCaseDAO(DatastoreProviderSingleton.getInstance().getDatastore());
    }
}

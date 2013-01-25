package model.dao;

import com.google.code.morphia.Datastore;
import model.ClinicalCase;

/**
 *
 */
public class ClinicalCaseDAO extends BaseMongoDAO<ClinicalCase>{
    public ClinicalCaseDAO(Datastore datastore) {
        super(ClinicalCase.class, datastore);
    }
}

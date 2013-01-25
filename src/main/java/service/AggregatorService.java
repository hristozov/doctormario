package service;

import model.ClinicalCase;
import server.providers.DaoProviderSingleton;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 */
@Path("insert")
public class AggregatorService {

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void insertClinicalCase(ClinicalCase clinicalCase) {
        // TODO: validation
        DaoProviderSingleton.getInstance().getDao().insert(clinicalCase);
    }

}

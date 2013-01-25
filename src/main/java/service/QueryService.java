package service;

import model.ClinicalCase;
import model.EvaluationResult;
import server.providers.ClassifierProviderSingleton;
import server.providers.DaoProviderSingleton;
import weka.classifiers.Evaluation;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 */
@Path("evaluate")
public class QueryService {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public EvaluationResult evaluateClinicalCase(ClinicalCase clinicalCase) {
        EvaluationResult result = new EvaluationResult();
        double resultProb[] = ClassifierProviderSingleton.getInstance().getClassifier().evaluate(clinicalCase);

        if (resultProb == null || resultProb.length != 2) {
            result.healthyProb = result.sickProb = 0;
            result.success = false;
            return result;
        }
        result.healthyProb = resultProb[0];
        result.sickProb = resultProb[1];
        result.success = true;
        return result;
    }
}

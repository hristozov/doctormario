package server.providers;

import model.ClinicalCase;
import server.aggregator.Aggregator;

/**
 * Created with IntelliJ IDEA.
 * User: gh
 * Date: 1/25/13
 * Time: 11:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class AggregatorProviderSingleton {
    private static AggregatorProviderSingleton ourInstance = new AggregatorProviderSingleton();

    private Aggregator<ClinicalCase> aggregator;

    public Aggregator<ClinicalCase> getAggregator() {
        return aggregator;
    }

    public static AggregatorProviderSingleton getInstance() {
        return ourInstance;
    }

    private AggregatorProviderSingleton() {
        // XXX: cast fail :)
        aggregator = new Aggregator<ClinicalCase>(DaoProviderSingleton.getInstance().getDao());
    }
}

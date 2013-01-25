package server.providers;

import server.classifier.Classifier;

/**
 * Created with IntelliJ IDEA.
 * User: gh
 * Date: 1/25/13
 * Time: 11:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class ClassifierProviderSingleton {
    private static ClassifierProviderSingleton ourInstance = new ClassifierProviderSingleton();

    private Classifier classifier;

    public Classifier getClassifier() {
        return classifier;
    }

    public static ClassifierProviderSingleton getInstance() {
        return ourInstance;
    }

    private ClassifierProviderSingleton() {
        classifier = new Classifier(DaoProviderSingleton.getInstance().getDao());
    }
}

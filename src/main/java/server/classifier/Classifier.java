package server.classifier;

import model.ClinicalCase;
import model.dao.GenericDAO;
import org.apache.log4j.Logger;
import server.classifier.dataset.ClinicalCaseAttributeCreator;
import server.classifier.instance.ClinicalCaseInstanceBuilder;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 */
public class Classifier {
    private GenericDAO dao;
    private Logger logger = Logger.getLogger(this.getClass());
    private boolean isReady = false;
    final private NaiveBayes classifier = new NaiveBayes();
    private Instances trainingSet;
    private Set<String> includedCases = new HashSet<String>();

    public boolean isReady() {
        return this.isReady;
    }

    public void checkForModifications() {
        for(ClinicalCase c : (List<ClinicalCase>)dao.findAll()) {
            if (!includedCases.contains(c.id)) {
                try {
                    classifier.updateClassifier(
                            new ClinicalCaseInstanceBuilder(8, c, trainingSet)
                                    .setIncludeClass(true)
                                    .toInstance());
                } catch (Exception e) {
                    logger.error("Fail", e);
                }
            }
        }
    }

    public Classifier(GenericDAO dao) {
        this.dao = dao;
        try {
            train();
        } catch (Exception e) {
            logger.error("Training failed!", e);
        }

        Timer t = new Timer();
        t.scheduleAtFixedRate(
                new Updater(this),
                60000,
                120000);
    }

    public double[] evaluate(ClinicalCase clinicalCase) {
        Instance instance =
                new ClinicalCaseInstanceBuilder(8, clinicalCase, trainingSet)
                        .setIncludeClass(false)
                        .toInstance();
        try {
            return classifier.distributionForInstance(instance);
        } catch (Exception e) {
            logger.error("We're fucked", e);
            return new double[]{};
        }

    }

    private void train() throws Exception {
        List<ClinicalCase> res = dao.findAll();

        FastVector attr = new ClinicalCaseAttributeCreator().toFastVector();
        trainingSet = new Instances("rel", attr, 10);
        trainingSet.setClassIndex(trainingSet.numAttributes() - 1);

        for (ClinicalCase clinicalCase : res) {
            includedCases.add(clinicalCase.id);
            trainingSet.add(
                    new ClinicalCaseInstanceBuilder(8, clinicalCase, trainingSet)
                            .setIncludeClass(true)
                            .toInstance());
        }

        classifier.buildClassifier(trainingSet);
        Evaluation ev = new Evaluation(trainingSet);
        ev.crossValidateModel(classifier, trainingSet, 10, new Random(1));
        logger.info(ev.toSummaryString());
        System.out.println(ev.toSummaryString());
        isReady = true;
    }


    private class Updater extends TimerTask {
        private Classifier classifier;

        @Override
        public void run() {
            classifier.checkForModifications();
        }

        public Updater(Classifier classifier) {
            this.classifier = classifier;
        }
    }
}

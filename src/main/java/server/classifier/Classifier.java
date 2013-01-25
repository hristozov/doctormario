package server.classifier;

import model.ClinicalCase;
import model.dao.GenericDAO;
import org.apache.log4j.Logger;
import server.classifier.dataset.ClinicalCaseAttributeCreator;
import server.classifier.instance.ClinicalCaseInstanceBuilder;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.VotedPerceptron;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;

import java.util.List;
import java.util.Random;

/**
 */
public class Classifier {
    private GenericDAO dao;
    private Logger logger = Logger.getLogger(this.getClass());
    private boolean isReady = false;
    private weka.classifiers.Classifier classifier;
    private Instances trainingSet;

    public boolean isReady() {
        return this.isReady;
    }

    public Classifier(GenericDAO dao) {
        this.dao = dao;
        try {
            train();
        } catch (Exception e) {
            logger.error("Training failed!", e);
        }
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
            trainingSet.add(
                    new ClinicalCaseInstanceBuilder(8, clinicalCase, trainingSet)
                            .setIncludeClass(true)
                            .toInstance());
        }

        //classifier = new VotedPerceptron();
        classifier = new NaiveBayes();

        classifier.buildClassifier(trainingSet);
        Evaluation ev = new Evaluation(trainingSet);
        ev.crossValidateModel(classifier, trainingSet, 10, new Random(1));
        logger.info(ev.toSummaryString());
        System.out.println(ev.toSummaryString());
        isReady = true;
    }

}

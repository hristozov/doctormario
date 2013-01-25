package server.classifier.instance;

import weka.core.Instance;
import weka.core.Instances;

/**
 *
 */
public abstract class InstanceBuilder<T> {
    protected T clinicalCase;
    protected Instance result;
    protected Instances dataset;
    protected boolean includeClass = true;

    public InstanceBuilder(int numAttributes, T clinicalCase, Instances dataset) {
        this.clinicalCase = clinicalCase;
        this.result = new Instance(numAttributes);
        this.dataset = dataset;
    }

    public InstanceBuilder setIncludeClass(boolean value) {
        this.includeClass = value;
        return this;
    }

    public abstract Instance toInstance();
}

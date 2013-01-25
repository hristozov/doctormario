package server.classifier.dataset;

import weka.core.FastVector;

/**
 * Created with IntelliJ IDEA.
 * User: gh
 * Date: 1/25/13
 * Time: 9:01 PM
 * To change this template use File | Settings | File Templates.
 */
public interface AttributeCreator {
    FastVector toFastVector();
}

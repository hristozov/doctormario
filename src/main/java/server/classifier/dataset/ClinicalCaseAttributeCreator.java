package server.classifier.dataset;

import server.classifier.nominal.ClinicalCaseNominalValue;
import weka.core.Attribute;
import weka.core.FastVector;

/**
 */
public class ClinicalCaseAttributeCreator implements AttributeCreator {
    @Override
    public FastVector toFastVector() {
        FastVector attributes = new FastVector(8);
        attributes.addElement(getAttribute("bloodPressure", new ClinicalCaseNominalValue[]{
                ClinicalCaseNominalValue.BLOOD_PRESSURE_LOW,
                ClinicalCaseNominalValue.BLOOD_PRESSURE_MEDIUM,
                ClinicalCaseNominalValue.BLOOD_PRESSURE_HIGH,
                ClinicalCaseNominalValue.BLOOD_PRESSURE_VERY_HIGH}));
        attributes.addElement(getAttribute("cholesterol", new ClinicalCaseNominalValue[]{
                ClinicalCaseNominalValue.CHOLESTEROL_LOW,
                ClinicalCaseNominalValue.CHOLESTEROL_MEDIUM,
                ClinicalCaseNominalValue.CHOLESTEROL_HIGH,
                ClinicalCaseNominalValue.CHOLESTEROL_VERY_HIGH}));
        attributes.addElement(getAttribute("bloodSugar", new ClinicalCaseNominalValue[]{
                ClinicalCaseNominalValue.BLOOD_SUGAR_OK,
                ClinicalCaseNominalValue.BLOOD_SUGAR_HIGH
        }));
        attributes.addElement(getAttribute("heartRate", new ClinicalCaseNominalValue[]{
                ClinicalCaseNominalValue.HEART_RATE_LOW,
                ClinicalCaseNominalValue.HEART_RATE_MEDIUM,
                ClinicalCaseNominalValue.HEART_RATE_HIGH
        }));
        attributes.addElement(getAttribute("exercise", new ClinicalCaseNominalValue[]{
                ClinicalCaseNominalValue.EXERCISE_NO,
                ClinicalCaseNominalValue.EXERCISE_YES
        }));
        attributes.addElement(getAttribute("sex", new ClinicalCaseNominalValue[]{
                ClinicalCaseNominalValue.SEX_FEMALE,
                ClinicalCaseNominalValue.SEX_MALE,
        }));
        attributes.addElement(getAttribute("age", new ClinicalCaseNominalValue[]{
                ClinicalCaseNominalValue.AGE_YOUNG,
                ClinicalCaseNominalValue.AGE_MID,
                ClinicalCaseNominalValue.AGE_OLD,
                ClinicalCaseNominalValue.AGE_VERY_OLD,
        }));
        attributes.addElement(getAttribute("result", new ClinicalCaseNominalValue[]{
                ClinicalCaseNominalValue.RESULT_NOT_SICK,
                ClinicalCaseNominalValue.RESULT_SICK,
        }));
        return attributes;
    }

    private Attribute getAttribute(String name, ClinicalCaseNominalValue[] nominalValues) {
        FastVector vector = new FastVector(nominalValues.length);
        for (ClinicalCaseNominalValue value : nominalValues) {
            vector.addElement(value.toString());
        }
        return new Attribute(name, vector);
    }
}

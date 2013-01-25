package server.classifier.instance;

import model.ClinicalCase;
import server.classifier.nominal.ClinicalCaseNominalValue;
import weka.core.Instance;
import weka.core.Instances;

/**
 */
public class ClinicalCaseInstanceBuilder extends InstanceBuilder<ClinicalCase> {
    public ClinicalCaseInstanceBuilder(int numAttributes, ClinicalCase clinicalCase, Instances dataset) {
        super(numAttributes, clinicalCase, dataset);
    }

    @Override
    public Instance toInstance() {
        Instance result = new Instance(8);
        result.setDataset(dataset);

        if (clinicalCase.bloodPressure < 134) {
            result.setValue(0, ClinicalCaseNominalValue.BLOOD_PRESSURE_LOW.toString());
        } else if (clinicalCase.bloodPressure < 142) {
            result.setValue(0, ClinicalCaseNominalValue.BLOOD_PRESSURE_MEDIUM.toString());
        } else if (clinicalCase.bloodPressure < 153) {
            result.setValue(0, ClinicalCaseNominalValue.BLOOD_PRESSURE_HIGH.toString());
        } else {
            result.setValue(0, ClinicalCaseNominalValue.BLOOD_PRESSURE_VERY_HIGH.toString());
        }


        if (clinicalCase.cholesterol < 188) {
            result.setValue(1, ClinicalCaseNominalValue.CHOLESTEROL_LOW.toString());
        } else if (clinicalCase.cholesterol < 217) {
            result.setValue(1, ClinicalCaseNominalValue.CHOLESTEROL_MEDIUM.toString());
        } else if (clinicalCase.cholesterol < 281) {
            result.setValue(1, ClinicalCaseNominalValue.CHOLESTEROL_HIGH.toString());
        } else {
            result.setValue(1, ClinicalCaseNominalValue.CHOLESTEROL_VERY_HIGH.toString());
        }

        if (clinicalCase.bloodSugar < 120) {
            result.setValue(2, ClinicalCaseNominalValue.BLOOD_SUGAR_OK.toString());
        } else {
            result.setValue(2, ClinicalCaseNominalValue.BLOOD_SUGAR_HIGH.toString());
        }

        if (clinicalCase.maxHeartRate < 111) {
            result.setValue(3, ClinicalCaseNominalValue.HEART_RATE_LOW.toString());
        } else if (clinicalCase.maxHeartRate < 152) {
            result.setValue(3, ClinicalCaseNominalValue.HEART_RATE_MEDIUM.toString());
        } else {
            result.setValue(3, ClinicalCaseNominalValue.HEART_RATE_HIGH.toString());
        }

        if (clinicalCase.exercise == 1) {
            result.setValue(4, ClinicalCaseNominalValue.EXERCISE_NO.toString());
        } else {
            result.setValue(4, ClinicalCaseNominalValue.EXERCISE_YES.toString());
        }

        if (clinicalCase.sex == 1) {
            result.setValue(5, ClinicalCaseNominalValue.SEX_MALE.toString());
        } else {
            result.setValue(5, ClinicalCaseNominalValue.SEX_FEMALE.toString());
        }

        if (clinicalCase.age < 33) {
            result.setValue(6, ClinicalCaseNominalValue.AGE_YOUNG.toString());
        } else if (clinicalCase.age < 40) {
            result.setValue(6, ClinicalCaseNominalValue.AGE_MID.toString());
        } else if (clinicalCase.age < 58) {
            result.setValue(6, ClinicalCaseNominalValue.AGE_OLD.toString());
        } else {
            result.setValue(6, ClinicalCaseNominalValue.AGE_VERY_OLD.toString());
        }

        if (includeClass) {
            switch(clinicalCase.result) {
                case 0:
                    result.setValue(7, ClinicalCaseNominalValue.RESULT_NOT_SICK.toString());
                    break;
                case 1:
                    result.setValue(7, ClinicalCaseNominalValue.RESULT_SICK.toString());
                    break;
            }
        }

        return result;
    }
}

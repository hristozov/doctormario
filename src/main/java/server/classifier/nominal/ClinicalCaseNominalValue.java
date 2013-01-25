package server.classifier.nominal;

/**
 * Created with IntelliJ IDEA.
 * User: gh
 * Date: 1/25/13
 * Time: 8:56 PM
 * To change this template use File | Settings | File Templates.
 */
public enum ClinicalCaseNominalValue {
    BLOOD_PRESSURE_LOW("0"),
    BLOOD_PRESSURE_MEDIUM("1"),
    BLOOD_PRESSURE_HIGH("2"),
    BLOOD_PRESSURE_VERY_HIGH("3"),

    CHOLESTEROL_LOW("0"),
    CHOLESTEROL_MEDIUM("1"),
    CHOLESTEROL_HIGH("2"),
    CHOLESTEROL_VERY_HIGH("3"),

    BLOOD_SUGAR_OK("0"),
    BLOOD_SUGAR_HIGH("1"),

    HEART_RATE_LOW("0"),
    HEART_RATE_MEDIUM("1"),
    HEART_RATE_HIGH("2"),

    EXERCISE_YES("0"),
    EXERCISE_NO("1"),

    SEX_FEMALE("0"),
    SEX_MALE("1"),

    AGE_YOUNG("0"),
    AGE_MID("1"),
    AGE_OLD("2"),
    AGE_VERY_OLD("3"),

    RESULT_NOT_SICK("0"),
    RESULT_SICK("1");

    private String nominalValue;

    ClinicalCaseNominalValue(String nominalValue) {
        this.nominalValue = nominalValue;
    }
}

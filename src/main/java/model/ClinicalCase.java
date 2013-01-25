package model;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;

/**
 *
 */
@Entity("clinicalCase")
public class ClinicalCase {
    @Id
    public String id;

    /**
        1. typical angina
        2. atypical angina
        3. non-anginal pain
        4. asymptomatic
     */
    // public int chestPain;

    public int bloodPressure;

    public int cholesterol;

    public int bloodSugar;

    /** 24-h max */
    public int maxHeartRate;

    public int exercise;

    public int sex;

    public int age;

    public int result;

    public ClinicalCase() {
        // for use by morphia
    }

    public ClinicalCase(int bloodPressure, int cholesterol, int bloodSugar, int maxHeartRate, int exercise, int sex, int age) {
        this.bloodPressure = bloodPressure;
        this.cholesterol = cholesterol;
        this.bloodSugar = bloodSugar;
        this.maxHeartRate = maxHeartRate;
        this.exercise = exercise;
        this.sex = sex;
        this.age = age;
    }
}

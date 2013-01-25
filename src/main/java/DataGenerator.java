import model.ClinicalCase;
import model.dao.ClinicalCaseDAO;
import model.dao.GenericDAO;
import server.Server;
import server.providers.DatastoreProviderSingleton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 */
public class DataGenerator implements Server {
    public static void main(String[] args) {
        new DataGenerator().start();
    }

    public GenericDAO dao = new ClinicalCaseDAO(DatastoreProviderSingleton.getInstance().getDatastore());

    @Override
    public void start() {
        Random r = new Random();
        List<ClinicalCase> cases = new ArrayList<ClinicalCase>();
        for (int i=0; i<5000; i++) {
            ClinicalCase c = new ClinicalCase();
            c.age = 40 + r.nextInt(20);
            c.sex = r.nextInt(2);
            c.bloodPressure = 135 + r.nextInt(40);
            c.exercise = (r.nextInt(10) < 7 ? 0 : 1);
            c.bloodSugar = (r.nextInt(10) < 7 ? 1 : 0);
            c.cholesterol = 190 + r.nextInt(30);
            c.maxHeartRate = 145 + r.nextInt(20);
            c.result = (r.nextInt(10) < 9 ? 1 : 0);
            cases.add(c);
        }
        for (int i=0; i<5000; i++) {
            ClinicalCase c = new ClinicalCase();
            c.age = 30 + r.nextInt(20);
            c.sex = r.nextInt(2);
            c.bloodPressure = 120 + r.nextInt(20);
            c.exercise = (r.nextInt(10) < 5 ? 0 : 1);
            c.bloodSugar = (r.nextInt(10) < 5 ? 1 : 0);
            c.cholesterol = 180 + r.nextInt(30);
            c.maxHeartRate = 140 + r.nextInt(20);
            c.result = (r.nextInt(10) < 5 ? 1 : 0);
            cases.add(c);
        }
        for (int i=0; i<5000; i++) {
            ClinicalCase c = new ClinicalCase();
            c.age = 20 + r.nextInt(20);
            c.sex = r.nextInt(2);
            c.bloodPressure = 110 + r.nextInt(15);
            c.exercise = (r.nextInt(10) < 7 ? 1 : 0);
            c.bloodSugar = (r.nextInt(10) < 7 ? 0 : 1);
            c.cholesterol = 170 + r.nextInt(20);
            c.maxHeartRate = 130 + r.nextInt(20);
            c.result = (r.nextInt(10) < 8 ? 0 : 1);
            cases.add(c);
        }
        Collections.shuffle(cases);
        for (ClinicalCase c : cases) {
            dao.insert(c);
        }
    }
}

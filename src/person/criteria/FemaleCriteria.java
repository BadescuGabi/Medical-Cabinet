package person.criteria;

import person.type.Patient;

import java.util.ArrayList;
import java.util.List;
import java.lang.*;

public class FemaleCriteria implements Criteria {
    @Override
    public List<Patient> meetCriteria(List<Patient> patients) {
        List<Patient> malePatients = new ArrayList<Patient>();
        for (Patient patient : patients) {
            if (patient.getGender().equalsIgnoreCase("FEMALE")) {
                malePatients.add(patient);
            }
        }
        return malePatients;
    }
}


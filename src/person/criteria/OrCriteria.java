package person.criteria;

import person.type.Patient;

import java.util.List;

public class OrCriteria implements Criteria {

    private Criteria criteria;
    private Criteria otherCriteria;

    public OrCriteria(Criteria criteria, Criteria otherCriteria) {
        this.criteria = criteria;
        this.otherCriteria = otherCriteria;
    }

    @Override
    public List<Patient> meetCriteria(List<Patient> Patients) {
        List<Patient> firstCriteriaItems = criteria.meetCriteria(Patients);
        List<Patient> otherCriteriaItems = otherCriteria.meetCriteria(Patients);

        for (Patient Patient : otherCriteriaItems) {
            if (!firstCriteriaItems.contains(Patient)) {
                firstCriteriaItems.add(Patient);
            }
        }
        return firstCriteriaItems;
    }
}
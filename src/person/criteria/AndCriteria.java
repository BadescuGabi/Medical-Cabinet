package person.criteria;

import person.type.Patient;

import java.util.List;

public class AndCriteria implements Criteria {

    private Criteria criteria;
    private Criteria otherCriteria;

    public AndCriteria(Criteria criteria, Criteria otherCriteria) {
        this.criteria = criteria;
        this.otherCriteria = otherCriteria;
    }

    @Override
    public List<Patient> meetCriteria(List<Patient> patients) {

        List<Patient> firstCriteriaPatients = criteria.meetCriteria(patients);
        return otherCriteria.meetCriteria(firstCriteriaPatients);
    }
}
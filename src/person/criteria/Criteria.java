package person.criteria;

import person.type.Patient;

import java.util.List;

public interface Criteria {
    List<Patient> meetCriteria(List<Patient> patients);
}

package person.criteria;

import person.Person;
import person.type.Patient;

import java.util.List;

public interface Criteria<T, U> {
    List<T> meetCriteria(List<U> personList);
}

package person.criteria;


import java.util.ArrayList;
import java.util.List;

public class PersonTypeCriteria<T, U> implements Criteria {
    private Class<T> type;

    public PersonTypeCriteria(Class<T> type) {
        this.type = type;
    }

    @Override
    public List<T> meetCriteria(List personList) {
        List<T> typeOfPersons = (List<T>) new ArrayList<U>();
        for (Object p : personList) {
            if (p.getClass() == type) {
                typeOfPersons.add((T) p);
            }
        }
        return typeOfPersons;
    }


}

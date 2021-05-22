package services;

import appointment.Appointment;
import appointment.actions.Vaccine;
import person.Person;
import person.criteria.PersonTypeCriteria;
import person.enums_and_salary.BloodGroup;
import person.type.Patient;

import java.util.List;
import java.util.Map;

import static medical_office.MedicalOffice.*;

public class ShowService {
    public static void showService(String option) {
        if (option.equals("3")) {
            for (Person p : persons)
                System.out.println(p.toString());
        }
        if (option.equals("4")) {
            for (Appointment p : appointments)
                System.out.println(p.toString());
        }
        if (option.equals("5")) {
            for (Map.Entry mapElement : bloodStock.entrySet()) {
                System.out.println("\nBlood Group: " + (BloodGroup) mapElement.getKey());
                System.out.println("\nQuantity" + (Double) mapElement.getValue() + "\n");
            }
        }
        if (option.equals("6")) {
            PersonTypeCriteria d = new PersonTypeCriteria<Patient, Person>(Patient.class); //returneaza pacientii din lista de persoane
            List<Patient> patients = d.meetCriteria(persons);
            int maxDonations = 0;
            patients.sort(Patient::compareTo);
            for (Patient i : patients) {
                if (maxDonations < i.getDonate())
                    maxDonations = i.getDonate();
                if (maxDonations == i.getDonate())
                    System.out.println(i);
                if (maxDonations > i.getDonate())
                    break;
            }
        }
        if (option.equals("7")) {
            for (Appointment i : appointments) {
                if (i.getClass() == Vaccine.class)
                    if (((Vaccine) i).getCovidAntibody() < 6)
                        System.out.println(i.getPatient().toString());
            }
        }
    }
}

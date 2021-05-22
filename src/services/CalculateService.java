package services;

import appointment.Appointment;
import appointment.actions.MedicalExamination;
import person.Person;
import person.criteria.PersonTypeCriteria;
import person.type.Doctor;
import person.type.Nurse;

import java.util.List;

import static medical_office.MedicalOffice.appointments;
import static medical_office.MedicalOffice.persons;

public class CalculateService {
    public static void calculateService (String option){
        if (option.equals("9")) {
            Double avg = 0.0;
            int nr=0;
            for (Appointment p : appointments) {
                if (p.getClass() == MedicalExamination.class) {
                    avg += ((MedicalExamination) p).calculateBMI().getSecond();
                    nr++;
                }
            }
            System.out.println("\nAverage BMI of patients: " + avg/nr);
        }
        if (option.equals("10")) {
            int totalSalary = 0;
            PersonTypeCriteria d = new PersonTypeCriteria<Doctor, Person>(Doctor.class);
            PersonTypeCriteria d1 = new PersonTypeCriteria<Nurse, Person>(Nurse.class);
            List<Doctor> doctors = d.meetCriteria(persons);
            List<Nurse> nurses = d1.meetCriteria(persons);
            for (Doctor i : doctors) {
                totalSalary += i.salary();
            }
            for (Nurse i : nurses) {
                totalSalary += i.salary();
            }
            System.out.println("\nSalariul total" + totalSalary);
        }
        if (option.equals("11")) {
            int income = 0;
            for (Appointment i : appointments) {
                income += i.calculatePrice();
            }
            System.out.println(income);
        }
    }
}

package meniu;

import appointment.Appointment;
import appointment.actions.BloodTransfusion;
import appointment.actions.MedicalExamination;
import appointment.actions.Ultrasound;
import appointment.actions.Vaccine;
import person.enums_and_salary.BloodGroup;
import person.Person;
import person.criteria.PersonTypeCriteria;
import person.type.Doctor;
import person.type.Nurse;
import person.type.Patient;

import java.text.ParseException;
import java.util.*;

import static medical_office.MedicalOffice.*;

public final class Service {

    private static Service service;

    private Service() {
    }

    public static Service getInstance() {
        if (service == null) {
            service = new Service();
        }

        return service;
    }

    public static void menu() throws ParseException {

        String option = "";
        while (!option.equals("0")) {
            System.out.println("           Welcome to our medical office");
            System.out.println("            ========Operations========");
            System.out.println("1:  Add person");
            System.out.println("2:  Add appointment");
            System.out.println("3:  Show persons");
            System.out.println("4:  Show appointments");
            System.out.println("5:  Calculate average BMI of patients");
            System.out.println("6:  Show blood stock for each blood group");
            System.out.println("7:  Show patient with most donations");
            System.out.println("8:  Delete patients with 0 blood donations");
            System.out.println("9:  Calculate total salary of employees");
            System.out.println("10: Calculate income of the cabinet");
            System.out.println("11: Show covid vulnerable patients\n");
            Scanner in = new Scanner(System.in);
            option = in.nextLine();

            if (option.equals("1")) {
                System.out.println("What type of person do you want to add: doctor/nurse/patient");
                String option2 = in.nextLine();
                if (option2.equalsIgnoreCase("doctor")) {
                    Person p = new Doctor();
                    persons.add(p);
                }
                if (option2.equalsIgnoreCase("patient")) {
                    Person p = new Patient();
                    persons.add(p);
                }
                if (option2.equalsIgnoreCase("nurse")) {
                    Person p = new Nurse();
                    persons.add(p);
                }
            }
            if (option.equals("2")) {
                System.out.println("What type of appointment do you want to add:\n-Blood transfusion\n-Medical examination\n-Ultrasound\n-Vaccine");
                String option2 = in.nextLine();
                if (option2.equalsIgnoreCase("blood transfusion")) {
                    Appointment p = new BloodTransfusion();
                    appointments.add(p);
                }
                if (option2.equalsIgnoreCase("medical examination")) {
                    Appointment p = new MedicalExamination();
                    appointments.add(p);
                }
                if (option2.equals("ultrasound")) {
                    Appointment p = new Ultrasound();
                    appointments.add(p);
                }
                if (option2.equals("vaccine")) {
                    Appointment p = new Vaccine();
                    appointments.add(p);
                }
            }
            if (option.equals("3")) {
                for (Person p : persons) {
                    System.out.println(p.toString());
                }
            }
            if (option.equals("4")) {
                for (Appointment p : appointments) {
                    System.out.println(p.toString());
                }
            }
            if (option.equals("5")) {
                Double avg = 0.0;

                for (Appointment p : appointments) {
                    if (p.getClass() == MedicalExamination.class) {
                        avg += ((MedicalExamination) p).calculateBMI().getSecond();
                    }
                }
                System.out.println("\nAverage BMI of patients: " + avg);
            }
            if (option.equals("6")) {
                for (Map.Entry mapElement : bloodStock.entrySet()) {
                    System.out.println("\nBlood Group: " + (BloodGroup) mapElement.getKey());
                    System.out.println("\nQuantity" + (Double) mapElement.getValue() + "\n");
                }
            }
            if (option.equals("7")) {
                PersonTypeCriteria d = new PersonTypeCriteria<Patient, Person>(Patient.class); //returneaza pacientii din lista de persoane
                List<Patient> patients = d.meetCriteria(persons);
                int maxDonations = 0;
                patients.sort(Patient::compareTo);
                for (Patient i : patients) {
                    if (maxDonations < i.getDonate()) {
                        maxDonations = i.getDonate();
                    }
                    if (maxDonations == i.getDonate()) {
                        System.out.println(i);
                    }
                    if (maxDonations > i.getDonate()) {
                        break;
                    }
                }
            }

            if (option.equals("8")) {
                for (int i = 0; i < persons.size(); i++) {
                    if (persons.get(i).getClass() == Patient.class) {
                        if (((Patient) persons).getDonate() == 0) {
                            persons.remove(i);
                            i--;
                        }
                    }
                }
            }
            if (option.equals("9")) {
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
            if (option.equals("10")) {
                int income = 0;
                for (Appointment i : appointments) {
                    income += i.calculatePrice();
                }
            }
            if (option.equals("11")) {
                for (Appointment i : appointments) {
                    if (i.getClass() == Vaccine.class) {
                        if (((Vaccine) i).getCovidAntibody() < 6) {
                            System.out.println(i.getPatient().toString());
                        }
                    }
                }
            }
        }
    }
}


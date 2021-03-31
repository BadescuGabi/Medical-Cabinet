package meniu;

import appointment.Appointment;
import appointment.actions.BloodTransfusion;
import appointment.actions.MedicalExamination;
import appointment.actions.Ultrasound;
import appointment.actions.Vaccine;
import medical_office.*;
import person.BloodGroup;
import person.Person;
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
                if (option2.equalsIgnoreCase("ultrasound")) {
                    Appointment p = new Ultrasound();
                    appointments.add(p);
                }
                if (option2.equalsIgnoreCase("vaccine")) {
                    Appointment p = new Vaccine();
                    appointments.add(p);
                }
            }
            if (option.equalsIgnoreCase("3")) {
                for (Person p : persons) {
                    System.out.println(p.toString());
                }
            }
            if (option.equalsIgnoreCase("4")) {
                for (Appointment p : appointments) {
                    System.out.println(p.toString());
                }
            }
            if (option.equalsIgnoreCase("5")) {
                Double avg = 0.0;

                for (Appointment p : appointments) {
                    if (p.getClass() == MedicalExamination.class) {
                        avg += ((MedicalExamination) p).calculateBMI().getSecond();
                    }
                }
                System.out.println("\nAverage BMI of patients: " + avg);
            }
            if (option.equalsIgnoreCase("6")) {
                for (Map.Entry mapElement : bloodStock.entrySet()) {
                    System.out.println("\nBlood Group: " + (BloodGroup) mapElement.getKey());
                    System.out.println("\nQuantity" + (Double) mapElement.getValue() + "\n");
                }
            }
        }
    }
}


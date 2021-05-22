package services;

import medical_office.MedicalOffice;
import person.type.Patient;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

import static medical_office.MedicalOffice.*;
import static services.AddService.addService;
import static services.CalculateService.calculateService;
import static services.ShowService.showService;

public final class Menu {

    private static Menu menu;

    private Menu() {
    }

    public static Menu getInstance() {
        if (menu == null) {
            menu = new Menu();
        }

        return menu;
    }

    public static void menu() throws ParseException, IOException {
        MedicalOffice.initializeMap();
        String option = "";
        ServiceCSV service= ServiceCSV.getInstance();
        doctors= ServiceCSV.readFromCSV("doctor","src/resources/Doctor.csv");
        patients= ServiceCSV.readFromCSV("patient","src/resources/Patient.csv");
        nurses= ServiceCSV.readFromCSV("nurse","src/resources/Nurse.csv");
        bloodTransfusions= ServiceCSV.readFromCSV("bloodtransfusion","src/resources/BloodTransfusion.csv");
        medicalExaminations= ServiceCSV.readFromCSV("medicalexamination","src/resources/MedicalExamination.csv");
        ultrasounds= ServiceCSV.readFromCSV("ultrasound","src/resources/Ultrasound.csv");
        vaccines= ServiceCSV.readFromCSV("vaccine","src/resources/Vaccine.csv");
        persons.addAll(doctors);
        persons.addAll(nurses);
        persons.addAll(patients);
        appointments.addAll(bloodTransfusions);
        appointments.addAll(medicalExaminations);
        appointments.addAll(ultrasounds);
        appointments.addAll(vaccines);

        while (!option.equals("0")) {
            System.out.println("           Welcome to our medical office");
            System.out.println("            ========Operations========");
            System.out.println("****Adding methods****");
            System.out.println("1:  Add person");
            System.out.println("2:  Add appointment");
            System.out.println("\n****Showing methods****");
            System.out.println("3:  Show persons");
            System.out.println("4:  Show appointments");
            System.out.println("5:  Show blood stock for each blood group");
            System.out.println("6:  Show patient with most donations");
            System.out.println("7: Show covid vulnerable patients");
            System.out.println("\n****Deleting methods****");
            System.out.println("8:  Delete patients with 0 blood donations");
            System.out.println("\n****Calculating methods****");
            System.out.println("9:  Calculate average BMI of patients");
            System.out.println("10:  Calculate total salary of employees");
            System.out.println("11: Calculate income of the cabinet");
            System.out.println("\n****Sort methods****");
            System.out.println("12: Sort patients by number of donations,name and age");
            System.out.println("\n0: exit from meniu");
            Scanner in = new Scanner(System.in);
            option = in.nextLine();

            if (option.equals("1"))
                addService("1");

            if (option.equals("2"))
              addService("2");

            if (option.equals("3"))
               showService("3");

            if (option.equals("4"))
               showService("4");

            if (option.equals("5"))
                showService("5");

            if (option.equals("6"))
                showService("6");

            if (option.equals("7"))
                showService("7");

            if (option.equals("8")) {
                for (int i = 0; i < persons.size(); i++) {
                    if (persons.get(i).getClass() == Patient.class) {
                        if (((Patient) persons.get(i)).getDonate() == 0) {
                            persons.remove(i);
                            i--;
                        }
                    }
                }
            }
            if (option.equals("9"))
               calculateService("9");

            if (option.equals("10"))
                calculateService("10");

            if (option.equals("11"))
                calculateService("11");

            if (option.equals("12")){
                persons.removeAll(patients);
                patients.sort(Patient::compareTo);
                persons.addAll(patients);
            }
        }
    }
}


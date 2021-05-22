package services;

import appointment.Appointment;
import appointment.actions.BloodTransfusion;
import appointment.actions.MedicalExamination;
import appointment.actions.Ultrasound;
import appointment.actions.Vaccine;
import person.Person;
import person.type.Doctor;
import person.type.Nurse;
import person.type.Patient;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

import static medical_office.MedicalOffice.appointments;
import static medical_office.MedicalOffice.persons;

public class AddService {
    public static void addService(String option) throws IOException, ParseException {
        Scanner in=new Scanner(System.in);
        if (option.equals("1")) {
            System.out.println("What type of person do you want to add: doctor/nurse/patient");
            String option2 = in.nextLine();
            if (option2.equalsIgnoreCase("doctor")) {
                Person p = new Doctor();
                persons.add(p);
                ServiceCSV.writeToCSV(p, "src/resources/Doctor.csv");
            }
            if (option2.equalsIgnoreCase("patient")) {
                Person p = new Patient();
                persons.add(p);
                ServiceCSV.writeToCSV(p, "src/resources/Patient.csv");
            }
            if (option2.equalsIgnoreCase("nurse")) {
                Person p = new Nurse();
                persons.add(p);
                ServiceCSV.writeToCSV(p, "src/resources/Nurse.csv");
            }
        }
        if (option.equals("2")) {
            System.out.println("What type of appointment do you want to add:\n-Blood transfusion\n-Medical examination\n-Ultrasound\n-Vaccine");
            String option2 = in.nextLine();
            if (option2.equalsIgnoreCase("blood transfusion")) {
                Appointment p = new BloodTransfusion();
                appointments.add(p);
                ServiceCSV.writeToCSV(p, "src/resources/BloodTransfusion.csv");
            }
            if (option2.equalsIgnoreCase("medical examination")) {
                Appointment p = new MedicalExamination();
                appointments.add(p);
                ServiceCSV.writeToCSV(p, "src/resources/MedicalExamination.csv");
            }
            if (option2.equalsIgnoreCase("ultrasound")) {
                Appointment p = new Ultrasound();
                appointments.add(p);
                ServiceCSV.writeToCSV(p, "src/resources/Ultrasound.csv");
            }
            if (option2.equalsIgnoreCase("vaccine")) {
                Appointment p = new Vaccine();
                appointments.add(p);
                ServiceCSV.writeToCSV(p, "src/resources/Vaccine.csv");
            }
        }
    }
}

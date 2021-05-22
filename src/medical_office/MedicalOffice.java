package medical_office;

import appointment.Appointment;
import appointment.actions.BloodTransfusion;
import appointment.actions.MedicalExamination;
import appointment.actions.Ultrasound;
import appointment.actions.Vaccine;
import person.*;
import person.enums_and_salary.BloodGroup;
import person.type.Doctor;
import person.type.Nurse;
import person.type.Patient;

import java.io.FileWriter;
import java.util.*;

public final class MedicalOffice {

    private static MedicalOffice INSTANCE;
    private final String adress;
    private final String name;
    public static List<Patient> vaccinatedPatients = new ArrayList<>();
    public static HashMap<BloodGroup, Double> bloodStock = new HashMap<>();
    public static List<Appointment> appointments = new ArrayList<>();

    public static List<Person> persons = new ArrayList<>();
    public static List<Doctor> doctors=new ArrayList<>();
    public static List<Patient> patients=new ArrayList<>();
    public static List<Nurse> nurses= new ArrayList<>();
    public static List<BloodTransfusion> bloodTransfusions=new ArrayList<>();
    public static List<MedicalExamination> medicalExaminations=new ArrayList<>();
    public static List<Ultrasound> ultrasounds=new ArrayList<>();
    public static List<Vaccine> vaccines=new ArrayList<>();
    public static FileWriter fileWriter;

    private MedicalOffice() {
        this.adress = "Splaiul Independentei 321B bloc D13 apartament 3";
        this.name = "Dr Gabi Badescu cabinet";
        bloodStock.put(BloodGroup.AB, 0.0);
        bloodStock.put(BloodGroup.B, 0.0);
        bloodStock.put(BloodGroup.A, 0.0);
        bloodStock.put(BloodGroup.ZERO, 0.0);
    }

    public static MedicalOffice getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MedicalOffice();
        }

        return INSTANCE;
    }

    public static void initializeMap(){
            bloodStock.put(BloodGroup.B,0.0);
            bloodStock.put(BloodGroup.ZERO,0.0);
            bloodStock.put(BloodGroup.A,0.0);
            bloodStock.put(BloodGroup.AB,0.0);
    }

    public String getAdress() {
        return adress;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "MedicalOffice{" +
                "adress='" + adress + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

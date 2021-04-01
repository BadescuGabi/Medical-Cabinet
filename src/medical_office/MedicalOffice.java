package medical_office;

import appointment.Appointment;
import person.*;
import person.enums_and_salary.BloodGroup;
import person.type.Patient;

import java.util.*;

public final class MedicalOffice {

    private static MedicalOffice INSTANCE;
    private final String adress;
    private final String name;
    public static List<Patient> vaccinatedPatients = new ArrayList<>(); /*todo*get list of vaccinated pacients*/
    public static HashMap<BloodGroup, Double> bloodStock = new HashMap<>();
    public static List<Appointment> appointments = new ArrayList<>();
    //public SortedSet<Doctor> doctors= new TreeSet<>();
    //public SortedSet<Nurse> nurses=new TreeSet<>();
    public static List<Person> persons = new ArrayList<>();

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

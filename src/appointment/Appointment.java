package appointment;

import person.type.Doctor;
import person.type.Patient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

import static medical_office.MedicalOffice.persons;

public abstract class Appointment {
    protected Date date;
    protected Patient patient;
    protected Doctor doctor;
    protected int price;
    protected int duration;

    public Appointment(String date, Patient patient, Doctor doctor, int price, int duration) throws ParseException { //
        this.date = new SimpleDateFormat("dd-MM-yyyy hh:mm").parse(date);
        this.patient = patient;
        this.doctor = doctor;
        this.duration = duration;
        this.price = price;
    }

    public Appointment() throws ParseException {
        Scanner in = new Scanner(System.in);
        System.out.println("\nDate and hour (dd-MM-yyyy hh:mm): ");
        String s = in.nextLine();
        this.date = new SimpleDateFormat("dd-MM-yyyy hh:mm").parse(s);
        System.out.println("\nAdd new oatient or existing one ?(1/2)");
        int op;
        op = in.nextInt();
        if (op == 1) {
            this.patient = new Patient();
        }
        if (op == 2) {
            System.out.println("\nChoose patient by corresponding index:\n");
            for (int i = 0; i < persons.size(); i++) {
                if (persons.get(i).getClass() == Patient.class) {
                    System.out.println(i + ":\n" + ((Patient) persons.get(i)).toString());
                }
            }
            op = in.nextInt();
            setPatient((Patient) persons.get(op));
        }
        System.out.println("\nChoose doctor by corresponding index:\n");
        for (int i = 0; i < persons.size(); i++) {
            if (persons.get(i).getClass() == Doctor.class) {
                System.out.println(i + ":\n" + ((Doctor) persons.get(i)).toString());
            }
        }
        op = in.nextInt();
        setDoctor((Doctor) persons.get(op));
        System.out.println("\nDuration (minutes) ");
        this.duration = in.nextInt();

    }

    public Date getDate() {
        return date;
    }

    public int getDuration() {
        return duration;
    }

    public Appointment setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public Appointment setDate(Date date) {
        this.date = date;
        return this;
    }

    public Patient getPatient() {
        return patient;
    }

    public Appointment setPatient(Patient patient) {
        this.patient = patient;
        return this;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Appointment setDoctor(Doctor doctor) {
        this.doctor = doctor;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return price == that.price && duration == that.duration && Objects.equals(date, that.date) && Objects.equals(patient, that.patient) && Objects.equals(doctor, that.doctor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, patient, doctor, price, duration);
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "date=" + date +
                ", patient=" + patient +
                ", price=" + price +
                ", duration=" + duration +
                ", doctor=" + doctor +
                '}';
    }

    public abstract int calculatePrice();
}

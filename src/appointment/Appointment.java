package appointment;

import person.type.Doctor;
import person.type.Patient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

import static medical_office.MedicalOffice.*;

public abstract class Appointment {
    protected String date;
    protected Patient patient;
    protected Doctor doctor;
    protected int price;
    protected int duration;
    protected int patientId;
    protected int nurseId;
    protected int doctorId; //pentru baza de date

    public Appointment(String date,int patientId,int doctorId,int duration){
        this.date=date;
        this.patientId=patientId;
        this.doctorId=doctorId;
        this.duration=duration;
    }
    public Appointment(String date, Patient patient, Doctor doctor, int price, int duration) throws ParseException {
        this.date = date;
        this.patient = patient;
        this.doctor = doctor;
        this.duration = duration;
        this.price = price;
    }

    public Appointment() throws ParseException {
        Scanner in = new Scanner(System.in);
        System.out.println("\nDate and hour (dd-MM-yyyy): ");
        String s = in.nextLine();
        this.date = s;
        System.out.println("\nAdd new oatient or existing one ?(1/2)");
        int op;
        op = in.nextInt();
        if (op == 1) {
            this.patient = new Patient();
        }
        if (op == 2) {
            System.out.println("\nChoose patient by corresponding index:\n");
            for (int i = 0; i < patients.size(); i++)
                System.out.println(patients.get(i).getId() + ":\n" + (patients.get(i)).toString());
            op = in.nextInt();
            for (int i = 0; i < patients.size(); i++)
                if (patients.get(i).getId() == op) {
                    patient = patients.get(i);
                    break;
                }
            System.out.println("\nChoose doctor by corresponding index:\n");
            for (int i = 0; i < doctors.size(); i++)
                System.out.println(doctors.get(i).getId() + ":\n" + (doctors.get(i)).toString());
            op = in.nextInt();
            for (int i = 0; i < doctors.size(); i++)
                if (doctors.get(i).getId() == op) {
                    doctor = doctors.get(i);
                    break;
                }
            System.out.println("\nDuration (minutes) ");
            this.duration = in.nextInt();

        }
    }

    public String getDate() {
        return date;
    }

    public int getPatientId() {
        return patientId;
    }

    public int getNurseId() {
        return nurseId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public int getDuration() {
        return duration;
    }

    public Appointment setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public Appointment setDate(String date) {
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
        if (!(o instanceof Appointment)) return false;
        Appointment that = (Appointment) o;
        return price == that.price && duration == that.duration && patientId == that.patientId && nurseId == that.nurseId && doctorId == that.doctorId && Objects.equals(date, that.date) && Objects.equals(patient, that.patient) && Objects.equals(doctor, that.doctor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, patient, doctor, price, duration, patientId, nurseId, doctorId);
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

    public int getPrice() {
        return price;
    }

    public Appointment setPrice(int price) {
        this.price = price;
        return this;
    }
}

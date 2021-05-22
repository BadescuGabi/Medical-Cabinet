package appointment.actions;

import appointment.Appointment;
import kotlin.Pair;
import person.type.Doctor;
import person.type.Patient;

import java.text.ParseException;
import java.util.Objects;
import java.util.Scanner;

public class MedicalExamination extends Appointment {
    private String description;
    private Pair<String, Double> results;

    public MedicalExamination(String date, Patient patient, Doctor doctor, int price, int duration, String description) throws ParseException {
        super(date, patient, doctor, price, duration);
        this.description = description;
        calculatePrice();
        calculateBMI();
    }

    public MedicalExamination() throws ParseException {
        Scanner in = new Scanner(System.in);
        System.out.println("\nDescription ");
        this.description = in.nextLine();

    }

    public String getDescription() {
        return description;
    }

    public MedicalExamination setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MedicalExamination)) return false;
        if (!super.equals(o)) return false;
        MedicalExamination that = (MedicalExamination) o;
        return Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), description);
    }

    @Override
    public String toString() {
        return "MedicalExamination{" +
                "description='" + description + '\'' +
                "} " + super.toString();
    }

    @Override
    public int calculatePrice() {
        if (patient.getAge() > 60) {
            price /= 3;
        }
        return price;
    }

    public Pair<String, Double> calculateBMI() {
        Double BMI = patient.getWeight() / Math.pow(patient.getHeight()/100  , 2);
        String info = "";
        if (BMI < 15 && patient.getGender().equals("MALE") || (BMI < 19 && patient.getGender().equals("FEMALE"))) {
            info = "excelent";
        } else if (BMI >= 15 && BMI < 27 && patient.getGender().equals("MALE") || (BMI >= 19 && BMI < 35 && patient.getGender().equals("FEMALE"))) {
            info = "normal";
        } else if ((BMI > 27 && patient.getGender().equals("MALE")) || (BMI >= 35 && patient.getGender().equals("FEMALE"))) {
            info = "overweight";
        }
        this.results = new Pair<>(info, BMI);
        return results;
    }
}

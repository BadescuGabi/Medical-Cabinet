package person.type;

import org.jetbrains.annotations.NotNull;
import person.enums_and_salary.BloodGroup;
import person.enums_and_salary.Gender;
import person.Person;

import java.util.Comparator;
import java.util.Objects;
import java.util.Scanner;

public class Patient extends Person implements Comparable<Patient> {
    private BloodGroup bloodGroup;
    private double height;
    private double weight;
    private static int patientsCount;
    private int donate;

    public Patient(String name, int age, Gender gender,int id, BloodGroup bloodGroup, double height, double weight, int donate) {
        super(name, age, gender,id);
        this.bloodGroup = bloodGroup;
        this.height = height;
        this.weight = weight;
        this.donate = donate;
        this.id=id;
    }

    public Patient() {
        System.out.println("\nBlood group (ZERO/A/B/AB): ");
        Scanner in = new Scanner(System.in);
        String s = in.next();
        this.bloodGroup = BloodGroup.valueOf(s.toUpperCase());
        System.out.println("\nHeight (cm): ");
        this.height = in.nextDouble();
        System.out.println("\nWeight (kg): ");
        this.weight = in.nextDouble();
    }



    public BloodGroup getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(BloodGroup bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public Patient setDonate(int donate) {
        this.donate = donate;
        return this;
    }

    public static int getPatientsCount() {
        return patientsCount;
    }

    public int getDonate() {
        return donate;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "bloodGroup=" + bloodGroup +
                ", height=" + height +
                ", weight=" + weight +
                ", number of donations=" + donate +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient)) return false;
        if (!super.equals(o)) return false;
        Patient patient = (Patient) o;
        return Double.compare(patient.height, height) == 0 && Double.compare(patient.weight, weight) == 0 && donate == patient.donate && bloodGroup == patient.bloodGroup;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), bloodGroup, height, weight, donate);
    }

    @Override
    public int compareTo(@NotNull Patient o) {
        return Comparator.comparing(Patient::getDonate)
                .thenComparing(Patient::getName)
                .thenComparingInt((Patient::getAge)).reversed()
                .compare(this, o);
    }
}

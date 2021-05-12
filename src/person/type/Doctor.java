package person.type;

import org.jetbrains.annotations.NotNull;
import person.enums_and_salary.Gender;
import person.Person;
import person.enums_and_salary.Salary;
import person.enums_and_salary.Specialization;

import java.util.Comparator;
import java.util.Objects;
import java.util.Scanner;

public class Doctor extends Person implements Salary, Comparable<Doctor> {
    private int exp;
    private Specialization spec;
    private int salaryPerMonth;

    public Doctor(String name, int age, Gender gender,int id, int exp, Specialization spec) {
        super(name, age, gender,id);
        this.exp = exp;
        this.spec = spec;
        this.salary();
        this.id=id;
    }

    public Doctor() {
        super();
        System.out.println("\nExperience (years): ");
        Scanner in = new Scanner(System.in);
        this.exp = in.nextInt();
        System.out.println("\nSpecialization (RADIOLOGIST/BLOOD_ANALYSIS/CONSULTANT");
        String s = in.next();
        this.spec= Specialization.valueOf(s.toUpperCase());
        this.salary();
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public Specialization getSpec() {
        return spec;
    }

    public void setSpec(Specialization spec) {
        this.spec = spec;
    }

    public int getSalaryPerMonth() {
        return salaryPerMonth;
    }
    
    @Override
    public int salary() {
        if (spec.equals(Specialization.RADIOLOGIST)) {
            if (exp <= 5) {
                this.salaryPerMonth = 750;
            }
            if (exp > 5 && exp < 10) {
                salaryPerMonth = 850;
            } else {
                salaryPerMonth = 950;
            }
        } else if (spec.equals(Specialization.CONSULTANT)) {
            if (exp <= 5) {
                salaryPerMonth = 860;
            }
            if (exp > 5 && exp < 10) {
                salaryPerMonth = 925;
            } else {
                salaryPerMonth = 1200;
            }
        } else if (spec.equals(Specialization.BLOOD_ANALYSIS)) {
            if (exp <= 5) {
                salaryPerMonth = 950;
            }
            if (exp > 5 && exp < 10) {
                salaryPerMonth = 1100;
            } else {
                salaryPerMonth = 1350;
            }
        }
        return salaryPerMonth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Doctor)) return false;
        if (!super.equals(o)) return false;
        Doctor doctor = (Doctor) o;
        return exp == doctor.exp && salaryPerMonth == doctor.salaryPerMonth && spec == doctor.spec;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), exp, spec, salaryPerMonth);
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "exp=" + exp +
                ", spec=" + spec +
                ", salaryPerMonth=" + salaryPerMonth +
                "} " + super.toString();
    }

    @Override
    public int compareTo(@NotNull Doctor o) {
        return Comparator.comparing(Doctor::getSalaryPerMonth)
                .reversed().compare(this, o);
    }
}

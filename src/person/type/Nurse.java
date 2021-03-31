package person.type;

import person.Gender;
import person.Person;
import person.Salary;


import java.util.Objects;
import java.util.Scanner;

public class Nurse extends Person implements Salary {
    private int exp;
    private int salaryPerMonth;

    public Nurse(String name, int age, Gender gender, int exp) {
        super(name, age, gender);
        this.exp = exp;
        this.salary();
    }

    public Nurse() {
        super();
        System.out.println("\nExperience (years): ");
        Scanner in = new Scanner(System.in);
        this.exp = in.nextInt();
        this.salary();
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getSalaryPerMonth() {
        return salaryPerMonth;
    }

    @Override
    public int salary() {
        if (exp < 5) {
            this.salaryPerMonth = 490;
        } else {
            this.salaryPerMonth = 650;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Nurse{" +
                "exp=" + exp +
                ", salaryPerMonth=" + salaryPerMonth +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Nurse nurse = (Nurse) o;
        return exp == nurse.exp && salaryPerMonth == nurse.salaryPerMonth;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), exp, salaryPerMonth);
    }
}



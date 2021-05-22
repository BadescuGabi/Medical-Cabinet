package person.type;

import person.enums_and_salary.Gender;
import person.Person;
import person.enums_and_salary.Employee;


import java.util.Objects;
import java.util.Scanner;

public class Nurse extends Person implements Employee {
    private int exp;
    private int salaryPerMonth;

    public Nurse(String name, int age, Gender gender, int id, int exp) {
        super(name, age, gender, id);
        this.exp = exp;
        this.salary();
        this.id = id;
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
        return salaryPerMonth;
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



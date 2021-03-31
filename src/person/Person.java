package person;

import org.jetbrains.annotations.NotNull;


import java.util.Comparator;
import java.util.Objects;
import java.util.Scanner;

public abstract class Person  {
    protected String name;
    protected int age;
    protected Gender gender;

    public Person(String name, int age, Gender gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public Person() {
        System.out.println("\nNume: ");
        Scanner in=new Scanner(System.in);
        this.name=in.nextLine();
        System.out.println("\nAge: ");
        this.age=in.nextInt();
        System.out.println("\nGender (MALE/FEMALE/OTHER): ");
        String s = in.next();
        this.gender= Gender.valueOf(s.toUpperCase());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender.toString();
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && name.equals(person.name) && gender == person.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, gender);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }
}

package person;

import person.enums_and_salary.Gender;


import java.util.Objects;
import java.util.Scanner;

public abstract class Person {
    protected String name;
    protected int age;
    protected Gender gender;
    private static int count = 0;
    protected int id;

    public Person(String name, int age, Gender gender, int id) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public Person() {
        System.out.println("\nNume: ");
        Scanner in = new Scanner(System.in);
        this.name = in.nextLine();
        System.out.println("\nAge: ");
        this.age = in.nextInt();
        System.out.println("\nGender (MALE/FEMALE/OTHER): ");
        String s = in.next();
        this.gender = Gender.valueOf(s.toUpperCase());
        count++;
        this.id = count;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Person.count = count;
    }

    public int getId() {
        return id;
    }

    public Person setId(int id) {
        this.id = id;
        return this;
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

    public Gender getGender() {
        return gender;
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

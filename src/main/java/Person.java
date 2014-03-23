package main.java;

public class Person {

    public final String name;
    public final int age;

    public Person(final String name, final int age) {
        this.name = name;
        this.age = age;
    }

    public Person(Person person) {
        this.name = person.name;
        this.age = person.age;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public int ageDifference(final Person other) {
        return age - other.age;
    }

    public String toString() {
        return String.format(
            "Person(%s, %s)", name, age);
    }
}

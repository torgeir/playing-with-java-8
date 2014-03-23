package main.java;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

public class Comparison {

    public static void print(String message,
                             List<Person> people) {
        System.out.println(message);
        people.forEach(System.out::println);
    }

    public static void main(String[] args) {

        final List<Person> people =
            Arrays.asList(
                new Person("John", 20),
                new Person("Sara", 21),
                new Person("Jane", 21),
                new Person("Greg", 35));


        print("ascending people age",
            people.stream()
                .sorted((p1, p2) -> p1.ageDifference(p2))
                .collect(toList())
        );


        print("ascending people age lambda",
            people.stream()
                .sorted(Person::ageDifference)
                .collect(toList())
        );


        Comparator<Person> compareAsc = (person1, person2) -> person1.ageDifference(person2);
        Comparator<Person> compareDesc = compareAsc.reversed();

        print("sorted asc by age",
            people.stream()
                .sorted(compareAsc)
                .collect(toList())
        );

        print("sorted desc by age",
            people.stream()
                .sorted(compareDesc)
                .collect(toList())
        );


        people.stream()
            .min(Person::ageDifference)
            .ifPresent(youngest -> System.out.println("Youngest " + youngest));


        people.stream()
            .max(Person::ageDifference)
            .ifPresent(eldest -> System.out.println("Youngest " + eldest));


        people.stream()
            .sorted((p1, p2) -> p1.name.compareTo(p2.name));


        Comparator<Person> byName = comparing(person -> person.name);
        System.out.println("ordered by name " +
            people.stream().sorted(byName).collect(toList()));


        Comparator<Person> byAge = comparing(person -> person.age);
        System.out.println("ordered by age " +
            people.stream().sorted(byAge).collect(toList()));


        System.out.println("ordered by age, then name" +
            people.stream().sorted(byAge.thenComparing(byName)).collect(toList()));


        List<Person> olderThan20 =
            people.stream()
                .filter(person -> person.age > 20)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        System.out.println("People older than 20 " + olderThan20);


        Map<Integer, List<Person>> peopleByAge =
            people.stream()
                .collect(Collectors.groupingBy(Person::getAge));
        System.out.println("People by age " + peopleByAge);


        Map<Integer, List<String>> nameOfPeopleByAge =
            people.stream()
                .collect(
                    groupingBy(Person::getAge,
                        mapping(Person::getName, toList()))
                );
        System.out.println("Name of people by age " + nameOfPeopleByAge);


        Map<Character, Optional<Person>> oldestPersonOfEachLetter =
            people.stream()
                .collect(
                    groupingBy(
                        person -> person.getName().charAt(0),
                        reducing(BinaryOperator.maxBy(comparing(Person::getAge))))
                );
        System.out.println("Oldest person of each letter " + oldestPersonOfEachLetter);
    }
}

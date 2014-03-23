package main.java;

import java.util.ArrayList;
import java.util.List;

import static main.java.Lambdas.friends;

public class Strings {

    public static void main(String[] args) {
        final List<String> uppercased =
            new ArrayList<>();

        for (String name : friends) {
            uppercased.add(name.toUpperCase());
        }
        System.out.println(uppercased);

        friends.stream()
            .map(name -> name.toUpperCase())
            .forEach(name -> System.out.print(name + " "));

        friends.stream()
            .map(name -> name.length())
            .forEach(count -> System.out.print(count + " "));

        friends.stream()
            .map(String::toUpperCase)
            .forEach(System.out::println);
        
    }
}

package main.java;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

public class Lambdas {

    public static final List<String> friends =
        asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");

    public static void main(String[] args) {

        // FunctionalInterface
        friends.forEach(new Consumer<String>() {
           @Override
            public void accept(final String name) {
               System.out.println(name);
            }
        });

        // Explicit
        friends.forEach((final String name) -> System.out.println(name));

        // Effectively final
        friends.forEach((name) -> System.out.println(name));

        // Single params without braces
        friends.forEach(name -> System.out.println(name));

        // Instance method reference
        friends.forEach(System.out::println);

        // "a".compareTo("c")
        Stream
            .of(new String[]{ "a", "c", "b" })
            .sorted(String::compareTo)
            .forEach(System.out::println);

    }
}

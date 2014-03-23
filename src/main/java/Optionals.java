package main.java;

import java.util.List;
import java.util.Optional;

public class Optionals {

    public static void pickName(
        final List<String> names,
        final String startingWith) {

        final Optional<String> foundName =
            names.stream()
            .filter(name -> name.startsWith(startingWith))
            .findFirst();

        System.out.println(
            String.format("A name starting with %s: %s",
                startingWith,
                foundName.orElse("No name found"))
        );
    }

    public static void main(String[] args) {
        pickName(Lambdas.friends, "N");
        pickName(Lambdas.friends, "B");
        pickName(Lambdas.friends, "Z");
    }
}

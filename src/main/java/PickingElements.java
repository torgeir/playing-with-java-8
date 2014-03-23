package main.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static main.java.Lambdas.friends;

public class PickingElements {

    public static void main(String[] args) {

        final List<String> startsWithN =
            new ArrayList<>();

        for (String name : friends) {
            if (name.startsWith("N")) {
                startsWithN.add(name);
            }
        }

        System.out.println(startsWithN);

        final List<String> startsWithN2 =
            friends.stream()
                .filter(name -> name.startsWith("N"))
                .collect(Collectors.toList());

        System.out.println(startsWithN2);

        final List<String> editors =
            Arrays.asList("Brian", "Jackie", "John", "Mike");

        final long countFriendsStartN =
            friends.stream().filter(name -> name.startsWith("N")).count();

        // no dry for u!
        final long countEditorsStartN =
            editors.stream().filter(name -> name.startsWith("N")).count();

        System.out.println(countFriendsStartN);
        System.out.println(countEditorsStartN);

        // ugh
        final Function<String, Predicate<String>> startsWithLetter =
            (String letter) -> {
                Predicate<String> checkStarts =
                    (String name) -> name.startsWith(letter);
                return checkStarts;
            };

        // :D
        final Function<String, Predicate<String>> startsWithLetter2 =
            letter -> name -> name.startsWith(letter);

        System.out.println(
            friends.stream().filter(startsWithLetter2.apply("N")).count());
    }

}

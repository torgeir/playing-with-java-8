package main.java;

import java.util.stream.Stream;

public class Objects {

    public static void main(String[] args) {

        Stream.of(new String[] { "one", null, "three" })
              .filter(java.util.Objects::nonNull)
              .map(String::toUpperCase)
              .forEach(System.out::println);
    }
}

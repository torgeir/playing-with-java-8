package main.java;

import java.io.File;
import java.util.stream.Stream;

public class Streams {

    public static void main(String[] args) {

        Stream.of(new String[] { "one", null, "three" })
            .filter(java.util.Objects::nonNull)
            .map(String::toUpperCase)
            .forEach(System.out::println);


        File currentDir = new File(".");
        File[] children = currentDir.listFiles();

//        for (File child : children) {
//            // 4 files, takes 4 seconds
//            processFile(child);
//        }

        Stream
            .of(children)
            .parallel() // takes 1 second
            .forEach(Streams::processFile);
    }

    private static void processFile(File file) {
        try { Thread.sleep(1000); } catch (Exception ignored) {}
        System.out.println(file);
    }
}

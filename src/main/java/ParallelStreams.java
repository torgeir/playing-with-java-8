package main.java;

import java.io.File;
import java.util.stream.Stream;

public class ParallelStreams {

    public static void main(String[] args) {

        File currentDir = new File(".");
        File[] children = currentDir.listFiles();

//        for (File child : children) {
//            // 4 files, takes 4 seconds
//            processFile(child);
//        }

        Stream
            .of(children)
            .parallel() // takes 1 second
            .forEach(ParallelStreams::processFile);
    }

    private static void processFile(File file) {
        try { Thread.sleep(1000); } catch (Exception ignored) {}
        System.out.println(file);
    }
}

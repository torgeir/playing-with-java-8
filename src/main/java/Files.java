package main.java;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Files {

    private static String DIR = ".";

    private static Stream<Path> files() throws IOException {
        return java.nio.file.Files.list(Paths.get(DIR));
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println("files");
        files()
            .forEach(System.out::println);

        System.out.println("dirs");
        files()
            .filter(java.nio.file.Files::isDirectory)
            .forEach(System.out::println);

        System.out.println(
            Arrays.toString(
                new File("src/main/java")
                    .list(new FilenameFilter() {
                        public boolean accept(final File dir, final String name) {
                            return name.endsWith(".java");
                        }
                    }))
        );

        System.out.println(Arrays.toString(new File(DIR).listFiles(File::isHidden)));


        List<File> filesHardWay = new ArrayList<>();
        File[] filesInCurrentDir = new File(".").listFiles();
        for (File file : filesInCurrentDir) {
            File[] filesInSubDir = file.listFiles();
            if (filesInSubDir == null) {
                filesHardWay.add(file);
            }
            else {
                filesHardWay.addAll(Arrays.asList(filesInSubDir));
            }
        }
        System.out.println(filesHardWay);


        final List<File> filesBetterWay =
            Stream.of(new File(".").listFiles())
                .flatMap(file ->
                    file.listFiles() == null
                        ? Stream.of(file)
                        : Stream.of(file.listFiles()))
            .collect(toList());
        System.out.println(filesBetterWay + "\n");


        new Thread(Files::waitForChange).start();
        Thread.sleep(2000);
        PrintWriter printWriter = new PrintWriter("tmp.txt");
        printWriter.write("works");
        printWriter.flush();
        printWriter.close();
    }

    public static void waitForChange() {
        try {
            System.out.println("Report any file changed within next 10 seconds...");
            final Path path = Paths.get(".");
            final WatchService watchService = path.getFileSystem().newWatchService();
            path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);

            final WatchKey key = watchService.poll(1L, TimeUnit.MINUTES);
            if (key != null)
                key.pollEvents()
                    .stream()
                    .forEach(event -> System.out.println("file changed/created: " + event.context()));

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

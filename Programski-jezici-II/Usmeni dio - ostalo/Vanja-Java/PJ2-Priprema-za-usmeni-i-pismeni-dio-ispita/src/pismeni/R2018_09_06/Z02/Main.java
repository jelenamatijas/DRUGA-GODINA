package pismeni.R2018_09_06.Z02;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Main {

    public static ArrayList<File> files = new ArrayList<>();
    public static ArrayList<Path> paths = new ArrayList<>();

    // TODO: proći do kraja args, primijeniti sve operacije, primjenjuje se posljednja navedena ako ima više istog tipa
    // na kraju upis u fajl ako ima ta komanda
    // kod mene se operacije primjenjuju sekvencijalno
    public static void main(String[] args) {
        config();
        try {
            for (int i = 0; i < args.length; i++) {
                switch (args[i]) {
                    case "--starts-with":
                        startsWith(args[++i]);
                        break;
                    case "--ends-with":
                        endsWith(args[++i]);
                        break;
                    case "--sort":
                        boolean res = args[++i].equals("asc") ? true : false;
                        sortAsc(res);
                        break;
                    case "--write-to-file":
                        printToFile(args[++i]);
                        break;
                    default:
                        System.out.println("nepoznata opcija");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void config() {
        File[] files = new File(System.getProperty("user.dir")).listFiles();
        for (int i = 0; i < files.length; i++) {
            paths.add(files[i].toPath());
        }
    }

    public static void startsWith(String pattern) {
        System.out.println("STARTS WITH " + pattern + " working directory " + System.getProperty("user.dir"));
        paths = paths.stream().filter(p -> p.getFileName().toString().startsWith(pattern)).collect(Collectors.toCollection(ArrayList::new));
        paths.forEach(System.out::println);
    }

    public static void endsWith(String pattern) {
        System.out.println("ENDS WITH " + pattern + " working directory " + System.getProperty("user.dir"));
        paths = paths.stream().filter(p -> p.getFileName().toString().endsWith(pattern)).collect(Collectors.toCollection(ArrayList::new));
        paths.forEach(System.out::println);
    }

    public static void sortAsc(boolean asc) throws IOException {
        if (asc) {
            System.out.println("SORT ASC");
            paths.sort((f1, f2) -> {
                try {
                    return Long.compare(Files.size(f1), Files.size(f2));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return 0;
            });
        } else {
            System.out.println("SORT DESC");
            paths.sort((f1, f2) -> {
                try {
                    return Long.compare(Files.size(f2), Files.size(f1));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return 0;
            });
        }
        paths.stream().forEach(f -> {
            try {
                System.out.println(f.getFileName() + " size: " + Files.size(f));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static void printToFile(String fileName) throws IOException {
        System.out.println("PRINT TO FILE " + fileName);
        File file = new File(fileName);
        file.createNewFile();
        try (PrintWriter pw = new PrintWriter(file)) {
            for (Path p : paths) {
                pw.println(p.getFileName() + " " + Files.size(p));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
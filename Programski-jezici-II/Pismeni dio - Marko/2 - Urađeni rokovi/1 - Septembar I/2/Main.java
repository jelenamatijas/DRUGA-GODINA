import java.io.File;

public class Main {

    private static int numberOfDeletedFiles;

    public static void deleteFilesRecursively(File directory, String key) {
        File[] files = directory.listFiles();

        if (files == null) {
            return;
        }

        for (File file : files) {
            if (file.isDirectory()) {
                deleteFilesRecursively(file, key);
            } else {
                String fileNameWithoutExtenstion = file.getName().split("\\.")[0];
                System.out.println(fileNameWithoutExtenstion);
                if (fileNameWithoutExtenstion.toLowerCase().contains(key.toLowerCase())) {
                    if (file.delete()) {
                        System.out.println("Obrisan fajl: " + file.getName());
                        numberOfDeletedFiles++;
                    } else {
                        System.out.println("Neuspjesno brisanje fajla: " + file.getName());
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("GRESKA: Broj argumenata razlicit od 2!");
            return;
        }

        String rootDirectoryPath = args[0];
        String key = args[1];

        File rootDirectory = new File(rootDirectoryPath);

        if (!rootDirectory.exists() || !rootDirectory.isDirectory()) {
            System.out.println("GRESKA: Putanja ne postoji ili putanja ne vodi do foldera!");
            return;
        }

        deleteFilesRecursively(rootDirectory, key);

        System.out.println("Broj obrisanih fajlova: " + numberOfDeletedFiles);
    }
}
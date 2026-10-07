package pismeni.R_2020_01_30.Z02;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class FolderWatcher extends Thread {

    String folder;
    ArrayList<String> txt = new ArrayList<>();

    public FolderWatcher(String folder) {
        setDaemon(true);
        this.folder = folder;
    }

    public void run() {
        while (!Sakupljac_Watchera.END) {
            try {
                File[] files = new File(folder).listFiles();
                for (File f : files) {
                    String filename = f.getName();
                    if (filename.endsWith(".txt") && !txt.contains(filename)) {
                        System.out.println(">>> NEW FILE" + filename + " FOLDER: " + folder);
                        Files.readAllLines(Path.of(folder, filename)).forEach(System.out::println);
                        txt.add(filename);
                    }
                }
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
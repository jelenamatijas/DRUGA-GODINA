package pismeni.R_2020_01_30.Z02;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Aktivator_Watchera extends Thread {
    long size = 0;

    public Aktivator_Watchera() {
        setDaemon(true);
    }

    public void run() {
        while (!Sakupljac_Watchera.END) {
            try {
                Path path = Path.of(Sakupljac_Watchera.datoteka);
                long uvecanaDatoteka = Files.size(path);
                if (size < uvecanaDatoteka) {
                    synchronized (Sakupljac_Watchera.lock) {
                        List<String> lines = Files.readAllLines(path);
                        String lastFolder = lines.get(lines.size() - 1);
                        System.out.println("Registrovan novi folder za praćenje " + lastFolder);
                        new FolderWatcher(lastFolder).start();
                    }
                    size = uvecanaDatoteka;
                }
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
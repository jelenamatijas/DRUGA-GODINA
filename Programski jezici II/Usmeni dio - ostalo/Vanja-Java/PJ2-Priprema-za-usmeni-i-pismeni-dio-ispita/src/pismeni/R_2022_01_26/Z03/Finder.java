package pismeni.R_2022_01_26.Z03;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.List;

public class Finder implements FileVisitor<Path> {
    private final PathMatcher matcher;
    public int brojKlasa = 0;

    public int packageNumber = 0;
    public HashMap<String, Integer> paketBrojKlasa = new HashMap<>(); // Path -> String

    public int brojLinija = 0;
    public int brojKomentara = 0;

    public Finder(String pattern) {
        this.matcher = FileSystems.getDefault().getPathMatcher("glob:" + pattern);
    }

    private void find(Path file) {
        Path name = file.getFileName();
        if (name != null && matcher.matches(name)) {
            brojKlasa++;
            //System.out.println(file);
            String filePath = file.toString();

            File javaFile = new File(filePath);
            File dir = new File(javaFile.getParent());
            Path p = Path.of(dir.getName());

            // ne radi
            if (paketBrojKlasa.containsKey(p.toString())) {
                Integer prije = paketBrojKlasa.get(p.toString());
                paketBrojKlasa.put(p.toString(), prije + 1);
                System.out.println(p);
            } else {
                paketBrojKlasa.put(p.toString(), 1);
            }

            try {
                List<String> linije = Files.readAllLines(file);
                brojLinija += linije.size();
                for (String linija : linije) {
                    if (linija.contains("\\") || linija.contains("/*")) {
                        brojKomentara += 1;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (file.toFile().isDirectory() && file.toFile().listFiles().length > 0)
            packageNumber++;
    }


    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
        find(dir);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        find(file);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        System.err.println(exc);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
        //find(dir);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public String toString() {
        return " Broj paketa " + packageNumber + " Ukupan broj klasa: " + brojKlasa + " Broj linija koda " + brojLinija +
                " Broj komentara " + brojKomentara;
    }
}
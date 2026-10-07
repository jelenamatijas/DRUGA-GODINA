package pismeni.R_2022_06_15.Z02;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;

public class Main {
    public static final String PREDMET = "predmet";
    public static final String PROFESOR = "profesor";
    public static final String STUDENT = "student";

    public static final String DEST_FILE = "src/R20220615/Z02/folder/predmeti/"; // TODO: Na ispitu drugačije

    private static ArrayList<Predmet> predmeti = new ArrayList<>();
    private static ArrayList<Profesor> profesori = new ArrayList<>();
    private static ArrayList<Student> studenti = new ArrayList<>();

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Nedostaje putanja direktorijuma");
            return;
        }

        try {
            WatchService service = FileSystems.getDefault().newWatchService();
            Path dir = Paths.get(args[0]);
            dir.register(service, ENTRY_CREATE);
            while (true) {
                WatchKey key = null;
                try {
                    key = service.take();
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
                for (WatchEvent<?> event : key.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();
                    WatchEvent<Path> ev = (WatchEvent<Path>) event;
                    Path filename = ev.context();
                    if (kind.equals(ENTRY_CREATE)) {
                        System.out.println("Kreiran novi fajl : " + filename);
                        glavnaMetoda(args[0], filename.toString());
                    }
                }

                boolean valid = key.reset();
                if (!valid)
                    break;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void glavnaMetoda(String folderPutanja, String nazivFajla) {

        if (nazivFajla.startsWith(PREDMET)) {
            Predmet p = procitajPredmet(folderPutanja + File.separator + nazivFajla);
            predmeti.add(p);
            // kreiraj folder u predmeti i podfoldere studenti i profesori
            // TODO new File().mkdir()
            Path predmetPath = Path.of(DEST_FILE + p.naziv);
            try {
                predmetPath.toFile().mkdir();
                Path.of(String.valueOf(predmetPath), "studenti").toFile().mkdir();
                Path.of(predmetPath.toString(), "profesori").toFile().mkdir();
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (Student s : studenti) {
                for (String sId : s.predmetId) {
                    if (sId.equals(p.id)) {
                        Path studentPath = Path.of(predmetPath.toString(), "studenti", s.brojIndeksa);
                        serialize(studentPath.toString(), s);
                    }
                }
            }
            for (Profesor prof : profesori) {
                for (String profId : prof.predmetId) {
                    if (profId.equals(p.id)) {
                        Path profPath = Path.of(predmetPath.toString(), "profesori", prof.jmb);
                        serialize(profPath.toString(), prof);
                    }
                }
            }
        } else if (nazivFajla.startsWith(PROFESOR)) {
            Profesor profesor = procitajProfesora(folderPutanja + File.separator + nazivFajla);
            profesori.add(profesor);
            // prođi kroz predmete, vidi jel se poklapa id, dodaj u folder sa nazivom predmeta
            // novi folder sa jmb profesora i serijalizovani fajl
            for (Predmet p : predmeti) {
                for (String id : profesor.predmetId) {
                    if (id.equals(p.id)) {
                        Path predmetPath = Path.of(DEST_FILE + p.naziv);
                        Path profPath = Path.of(predmetPath.toString(), "profesori", profesor.jmb);
                        try {
                            profPath.toFile().mkdir();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        serialize(profPath.toString(), profesor);
                    }
                }
            }
        } else if (nazivFajla.startsWith(STUDENT)) {
            Student s = procitajStudenta(folderPutanja + File.separator + nazivFajla);
            studenti.add(s);
            for (Predmet p : predmeti) {
                for (String id : s.predmetId) {
                    if (id.equals(p.id)) {
                        Path predmetPath = Path.of(DEST_FILE + p.naziv);
                        Path studentPath = Path.of(predmetPath.toString(), "studenti", s.brojIndeksa);
                        try {
                            studentPath.toFile().mkdir();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        serialize(studentPath.toString(), s);
                    }
                }
            }
            // prođi kroz predmete, vidi jel se poklapa id, dodaj u folder sa nazivom predmeta
            // novi folder sa naziv+indeks studenta i serijalizovani fajl
        }
    }


    private static Student procitajStudenta(String nazivFajla) {
        Student s = null;
        try {
            List<String> linije = Files.readAllLines(Path.of(nazivFajla));
            String predmetId = linije.get(4);
            String[] ids = predmetId.split(",");
            s = new Student(linije.get(1), linije.get(2), linije.get(3), new ArrayList<>(Arrays.asList(ids)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    private static Profesor procitajProfesora(String nazivFajla) {
        Profesor p = null;
        try {
            List<String> linije = Files.readAllLines(Path.of(nazivFajla));
            String predmetId = linije.get(4);
            String[] ids = predmetId.split(",");
            p = new Profesor(linije.get(1), linije.get(2), linije.get(3), new ArrayList<>(Arrays.asList(ids)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return p;
    }

    private static Predmet procitajPredmet(String nazivFajla) {
        Predmet p = null;
        try {
            List<String> linije = Files.readAllLines(Path.of(nazivFajla));
            p = new Predmet(linije.get(1), linije.get(2));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return p;
    }

    private static void serialize(String path, Student s) {
        try (
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path + File.separator + s.brojIndeksa + ".ser"))) {
            out.writeObject(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void serialize(String path, Profesor s) {
        try (
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path + File.separator + s.jmb + ".ser"))) {
            out.writeObject(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
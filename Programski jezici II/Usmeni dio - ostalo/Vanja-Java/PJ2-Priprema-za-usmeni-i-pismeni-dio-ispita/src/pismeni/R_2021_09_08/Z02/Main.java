package pismeni.R_2021_09_08.Z02;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class Main {

    public static ArrayList<Student> studenti = new ArrayList<>();

    public static void main(String[] args) {
        generisiStudente();

        System.out.println("Ukupan broj studenta po godini studija");
        Map<Integer, List<Student>> mapa = studenti.stream().collect(Collectors.groupingBy(s -> s.godinaStudija));
        mapa.forEach((key, value) -> System.out.println("godina studija " + key + " br studenta: " + value.size()));

        System.out.println("Tri najcesca prezimena:");
        studenti.stream().collect(Collectors.groupingBy(s -> s.prezime)).entrySet()
                .stream().sorted((e1, e2) -> e2.getValue().size() - e1.getValue().size()).limit(3)
                .forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue().size()));

        System.out.println("Najcesce ime studenta");
        studenti.stream().collect(Collectors.groupingBy(s -> s.ime)).entrySet()
                .stream().sorted((e1, e2) -> e2.getValue().size() - e1.getValue().size()).limit(1)
                .forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue().size()));

        System.out.println("Prikaz svih studenata grupisanih po godinama starosti na sljedeci nacin: 18 - 23, 24 - 29, 30 - 35");
        studenti.stream().collect(Collectors.groupingBy(s -> {
            int godina = 2022;
            int broj = godina - s.godinaRodj;
            if (broj >= 18 && broj <= 23)
                return "18 - 23 ";
            else if (broj >= 24 && broj <= 29)
                return "24 - 29 ";
            else
                return "30 -35 ";
        })).forEach((key2, value1) -> System.out.println(key2 + " " + value1.size()));

        System.out.println("\nPrikaz svih studenata grupisanih po prezimenu");
        studenti.stream().collect(Collectors.groupingBy(s -> s.prezime)).forEach((key1, value) -> System.out.println(key1 + " " + value));

        System.out.println("\nPrikaz svih studenata grupisanih po prezimenu po opadajucem redoslijedu");
        studenti.stream().collect(Collectors.groupingBy(s -> s.prezime)).entrySet().stream()
                .sorted((e1, e2) -> e2.getKey().compareTo(e1.getKey())).forEach(e -> System.out.println(e.getKey() + " " + e.getValue()));

        System.out.println("\nPrikaz najboljeg studenta za svaku godinu studija");
        Map<Integer, List<Student>> map = studenti.stream().collect(Collectors.groupingBy(s -> s.godinaStudija));
        map.forEach((key, stud) -> {
            stud.sort((s1, s2) -> Double.compare(s2.ocjena, s1.ocjena));
            System.out.println(key + " godina studija   najbolji student " + stud.get(0));
        });

        System.out.println("Prikaz svih razlicitih godina rodenja u formatu godina1#godina2#...koristenjem reduce metode");
        String rez = studenti.stream().mapToInt(s -> s.godinaRodj).distinct().sorted()
                .mapToObj(String::valueOf).reduce("", (s1, s2) -> s1 + "#" + s2);
        System.out.println(rez);
    }

    public static void generisiStudente() {
        Random rand = new Random();
        String[] slova = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        for (int i = 0; i < 80; i++) {
            Student s = new Student(slova[rand.nextInt(26)] + "Ime", slova[rand.nextInt(26)] + "Prezime",
                    Integer.toString(rand.nextInt(2000)), rand.nextInt(17) + 1987, rand.nextInt(4) + 1, rand.nextDouble() * 10);
            studenti.add(s);
            //System.out.println(s);
        }
    }
}
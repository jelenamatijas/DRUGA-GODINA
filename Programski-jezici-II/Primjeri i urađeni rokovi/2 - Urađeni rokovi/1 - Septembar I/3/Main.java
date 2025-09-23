import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Main {

    public static <T extends Podatak> List<T> filtrirajSortiraj(List<Predicate<T>> uslovi, int pocetniIndeks,
	int krajnjiIndeks, List<T>... listePodataka) {
        List<T> rezultati = new ArrayList<>();

        for (List<T> lista : listePodataka) {
            for (T element : lista) {
                boolean zadovoljavaUslov = uslovi.stream().allMatch(predikat -> predikat.test(element));
                if (zadovoljavaUslov) {
                    rezultati.add(element);
                }
            }
        }

        rezultati.sort((a, b) -> Integer.compare(a.hashCode(), b.hashCode()));

        return rezultati.subList(pocetniIndeks, Math.min(krajnjiIndeks, rezultati.size()));
    }

    public static void main(String args[]) {

        // Pogledati metodu asList* - ne mora se koristiti
        List<Student> lista1 = Arrays.asList(new Student("Marko", "Dunovic", "1166/22"),
                new Student("Andrej", "Tomic", "1106/22"), new Student("Danilo", "Todorovic", "1166/22"));

        List<Student> lista2 = Arrays.asList(new Student("Luka", "Mikanović", "1144/22"),
                new Student("Dragan", "Colic", "1139/22"), new Student("Nikola", "Zezelj", "1167/22"));

        List<Student> lista3 = Arrays.asList(new Student("Damjan", "Štrbac", "1139/22"),
                new Student("Boris", "Matic", "1181/22"), new Student("Milan", "Latinovic", "1164/22"));

        Predicate<Student> imePocinjeSaM = student -> (student.getIme().startsWith("M") || student.getIme().startsWith("A"));
        Predicate<Student> prezimeZavrsavaNaIc = student -> student.getPrezime().endsWith("ic");
        Predicate<Student> indeksSadrzi22 = student -> student.getIndeks().contains("22");

        List<Predicate<Student>> uslovi = Arrays.asList(imePocinjeSaM, prezimeZavrsavaNaIc, indeksSadrzi22);

        // Posljednja tri argumenta je lista lista
        List<Student> rezultati = filtrirajSortiraj(uslovi, 0, 3, lista1, lista2, lista3);

        System.out.println("Rezultati: ");
        rezultati.forEach(System.out::println);
    }
}
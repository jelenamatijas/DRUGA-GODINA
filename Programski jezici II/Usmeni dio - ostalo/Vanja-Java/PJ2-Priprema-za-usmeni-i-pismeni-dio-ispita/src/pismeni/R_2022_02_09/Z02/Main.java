package pismeni.R_2022_02_09.Z02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// Lab 13
public class Main {
    public static void main(String[] args) {
        Path path = Path.of("src/pismeni.R_2022_02_09/Z02/movies.txt");
        List<String> linije;
        try {
            linije = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        ArrayList<Movie> movies = new ArrayList<>();
        for (String linija : linije) {
            if (linija.startsWith("movie_id"))
                continue;
            String[] arg = linija.split("###");
            String posljednji = arg[12].substring(0, arg[12].indexOf(";"));
            Movie m = new Movie(Integer.parseInt(arg[0]), arg[1], Integer.parseInt(arg[2]), arg[3], arg[4], Double.parseDouble(arg[5]),
                    arg[6], Integer.parseInt(arg[7]), Integer.parseInt(arg[8]), arg[9], arg[10], Double.parseDouble(arg[11]), Integer.parseInt(posljednji));
            movies.add(m);
        }

        // a1
        Map<String, List<Movie>> mapa = movies.stream().collect(Collectors.groupingBy(m -> m.release_date));
        mapa.entrySet().forEach(System.out::println);

        // b
        movies.stream().filter(m -> m.vote_average > 8.2).forEach(System.out::println);

        // c
        movies.stream().filter(m -> m.budget > 10_000_000).forEach(System.out::println);

        // d
        movies.stream().filter(m -> {
            Integer godina = Integer.parseInt(m.release_date.split("-")[0]);
            return godina > 2000 && godina <= 2010;
        }).forEach(System.out::println);

        // TODO:  e. kreirati listu filmova grupisanih po ocjeni u formatu npr. 6,0-6,99, 7,0-7,99,...,9.0-10.0,
        System.out.println("kreirati listu filmova grupisanih po ocjeni u formatu npr. 6,0-6,99, 7,0-7,99,...,9.0-10.0,");
        movies.stream().collect(Collectors.groupingBy(m -> {
            if (m.vote_average >= 10.00)
                return 9;
            else if (m.vote_average >= 6.00)
                return (int) m.vote_average;
            return 0;
        })).entrySet().stream().skip(1).forEach(e -> System.out.println(e.getKey() + "+" + "\t" + e.getValue()));
        movies.stream().collect(Collectors.groupingBy(m -> m.release_date)).entrySet().forEach(System.out::println);

        // f
        System.out.println("Prosječna oscjena filmova 90-ih " + movies.stream().filter(m -> {
            Integer godina = Integer.parseInt(m.release_date.split("-")[0]);
            return godina > 1990 && godina < 2000;
        }).mapToDouble(m -> m.vote_average).average().getAsDouble());

        // g
        System.out.println("Ukupni budžet filmova snimljenih 80-ih godina " + movies.stream().filter(m -> {
            int godina = Integer.parseInt(m.release_date.split("-")[0]);
            return godina > 1980 && godina < 1990;
        }).mapToInt(m -> m.budget).sum());
    }
}
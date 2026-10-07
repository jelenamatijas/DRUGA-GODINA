package pismeni.R_2022_06_15.Z03;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Main {

    public static <T, V> void processData(List<Predicate<Data<T, V>>> predikati, Consumer<Data<T, V>> consumer, List<Data<T, V>>... listaPodataka) {
        ArrayList<Data<T, V>> podaci = new ArrayList<>();
        for (List<Data<T, V>> lista : listaPodataka) {
            for (Data<T, V> data : lista) {
                if (predikati.stream().allMatch(dataPredicate -> dataPredicate.test(data))) {
                    podaci.add(data);
                }
            }
        }
        podaci.stream().sorted(Comparator.comparingInt(Object::hashCode)).forEach(consumer); // (d1, d2) -> d1.hashCode() - d2.hashCode()
    }

    public static void main(String[] args) {
        List<Data<String, Integer>> lista1 = new ArrayList<>();

        lista1.add(new ExampleData1<>("type1", 10, "red"));
        lista1.add(new ExampleData1<>("type1", 20, "green"));
        lista1.add(new ExampleData1<>("type1", 30, "blue"));

        List<Data<String, Integer>> lista2 = new ArrayList<>();

        lista2.add(new ExampleData2<>("type2", 10, 1));
        lista2.add(new ExampleData2<>("type2", 20, 2));
        lista2.add(new ExampleData2<>("type2", 30, 3));

        List<Predicate<Data<String, Integer>>> predikati = new ArrayList<>();
        predikati.add(d -> d.getType().equals("type1"));
        predikati.add(d -> d.getValue() >= 20);
        predikati.add(d -> d.getValue() < 100);

        Consumer<Data<String, Integer>> consumer = d -> System.out.println("T : " + d.getType() + " V: " + d.getValue());
        processData(predikati, consumer, lista1, lista2);
    }
}
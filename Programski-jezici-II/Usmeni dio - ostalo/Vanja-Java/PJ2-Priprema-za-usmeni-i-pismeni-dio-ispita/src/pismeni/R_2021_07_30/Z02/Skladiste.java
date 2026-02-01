package pismeni.R_2021_07_30.Z02;

import java.util.List;
import java.util.PriorityQueue;
import java.util.function.Predicate;

public class Skladiste<T extends Podatak> {
    private PriorityQueue<T> red = new PriorityQueue<>();

    public void dodaj(T t) {
        red.add(t);
    }

    public T uzmiPrvi() {
        return red.poll();
    }

    public void ispis() {
        red.forEach(System.out::println);
    }

    public void izvrsiAkcije() {
        red.forEach(e -> e.akcija());
    }

    public void pretraga(List<Predicate<T>> lista) {
        for (T t : red) {
            boolean ok = true;
            for (Predicate<T> p : lista) {
                if (!p.test(t)) {
                    ok = false;
                    break;
                }
            }
            if (ok)
                System.out.println(t);
        }
    }

}
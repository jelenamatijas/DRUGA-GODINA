package pismeni.R_2023_04_26.Z1;

import java.time.LocalDateTime;

public class Poruka implements Comparable<Poruka> {

    Prioritet prioritet;
    String opis;
    LocalDateTime vrijeme;

    public Poruka(Prioritet prioritet, String opis) {
        this.prioritet = prioritet;
        this.opis = opis;
        this.vrijeme = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return prioritet + " " + opis + " " + vrijeme;
    }

    @Override
    public int compareTo(Poruka p) {
        if (prioritet.equals(Prioritet.KRITICNO))
            return -1;
        else if (prioritet.equals(Prioritet.UPOZORENJE))
            return 0;
        else return 1;
    }
}
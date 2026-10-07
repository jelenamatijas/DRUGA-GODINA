package pismeni.R_2022_06_15.Z01;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

public class Nadzornik extends Stanovnik {

    private static final String PORUKA = "Zbog neadekvatnog ponašanja, vaša plata će biti umanjena za %d";

    public Nadzornik(String ime, String prezime, String jmb) {
        super(ime, prezime, jmb);
    }

    @Override
    public void run() {

    }

    public ArrayList<String> pretragaPoKljucnimRijecima(String[] rijeci) {
        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        ArrayList<String> jmbPosiljaoca = new ArrayList<>();
        for (Poruka poruka : Simulacija.poruke) {
            if (poruka.vrijemeSlanja.startsWith(date)) {
                for (String rijec : rijeci) {
                    if (poruka.tekst.contains(rijec)) {
                        if (!jmbPosiljaoca.contains(poruka.jmbPosiljaoca))
                            jmbPosiljaoca.add(poruka.jmbPosiljaoca);
                    }
                }
            }
        }
        return jmbPosiljaoca;
    }
}
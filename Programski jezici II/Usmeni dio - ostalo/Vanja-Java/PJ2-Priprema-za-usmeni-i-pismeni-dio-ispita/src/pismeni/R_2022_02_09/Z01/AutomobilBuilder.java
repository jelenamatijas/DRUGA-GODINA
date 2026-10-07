package pismeni.R_2022_02_09.Z01;

import java.util.ArrayList;
import java.util.List;

public class AutomobilBuilder implements VoziloBuilder {
    @Override
    public Vozilo build(List<String> linije) {
        String id = "";
        Vozac vozac = null;
        Motor motor = null;
        String konfig = "";
        ArrayList<Supermoc> supermoci = new ArrayList<>();
        for (String linija : linije) {
            String[] params = linija.split(";");
            if (linija.startsWith("AUTOMOBIL")) {
                id = params[1];
                konfig = params[2];
            } else if (linija.startsWith("MOTOR")) {
                motor = new Motor(Integer.parseInt(params[2]), TipMotora.valueOf(params[3].toUpperCase()));
            } else if (linija.startsWith("VOZAC")) {
                vozac = new Vozac(params[2], params[3]);
            } else if (linija.startsWith("SUPER_MOC")) {
                supermoci.add(new Supermoc(params[2]));
            }
        }
        return new Automobil(id, vozac, motor, konfig, supermoci);
    }
}
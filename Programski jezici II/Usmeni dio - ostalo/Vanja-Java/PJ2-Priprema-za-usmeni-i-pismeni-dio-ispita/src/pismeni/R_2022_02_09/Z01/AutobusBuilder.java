package pismeni.R_2022_02_09.Z01;

import java.util.List;

public class AutobusBuilder implements VoziloBuilder {
    @Override
    public Vozilo build(List<String> linije) {
        String id = "";
        Vozac vozac = null;
        Motor motor = null;
        String konfig = "";
        int brojMjesta = 0;
        for (String linija : linije) {
            String[] params = linija.split(";");
            if (linija.startsWith("AUTOBUS")) {
                id = params[1];
                konfig = params[2];
                brojMjesta = Integer.parseInt(params[3]);
            } else if (linija.startsWith("MOTOR")) {
                motor = new Motor(Integer.parseInt(params[2]), TipMotora.valueOf(params[3].toUpperCase()));
            } else if (linija.startsWith("VOZAC")) {
                vozac = new Vozac(params[2], params[3]);
            }
        }
        return new Autobus(id, vozac, motor, konfig, brojMjesta);
    }
}
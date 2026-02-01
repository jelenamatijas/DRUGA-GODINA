package pismeni.R_2022_02_09.Z01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

public class Simulacija {

    static Polje[] polja;
    static HashMap<String, VoziloBuilder> bilderi = new HashMap<>();

    static final String AUTOBUS = "AUTOBUS";
    static final String AUTOMOBIL = "AUTOMOBIL";
    static final String KAMION = "KAMION";

    static BlockingQueue<String> redoslijed = new LinkedBlockingQueue<>();

    public static void main(String[] args) throws InterruptedException {
        // parsiraj stazu
        Path staza = Path.of("src/pismeni.R_2022_02_09/Z01/staza.csv");
        Path vozila = Path.of("src/pismeni.R_2022_02_09/Z01/vozila.csv");

        List<String> stazaLinije = null;
        try {
            stazaLinije = Files.readAllLines(staza);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        polja = new Polje[stazaLinije.size()];
        int i = 0;
        for (String linija : stazaLinije) {
            String[] par = linija.split(";");
            if (par[0].startsWith(TipPolja.OBICNO.name())) {
                polja[i++] = new Polje();
            } else if (par[0].startsWith(TipPolja.NERAVNO.name())) {
                polja[i++] = new NeravnoPolje();
            } else if (par[0].startsWith(TipPolja.KLIZAVO.name())) {
                int mogucnost = Integer.parseInt(par[1].substring(0, par[1].length() - 1));
                polja[i++] = new KlizavoPolje(mogucnost);
            }
        }
        //Arrays.stream(polja).forEach(System.out::println);
        // parsiraj vozila
        bilderi.put(AUTOBUS, new AutobusBuilder());
        bilderi.put(AUTOMOBIL, new AutomobilBuilder());
        bilderi.put(KAMION, new KamionBuilder());
        List<Vozilo> vozilaObj = new ArrayList<>();
        try {
            List<String> linije = Files.readAllLines(vozila);
            Map<String, List<String>> mapa = linije.stream().collect(Collectors.groupingBy(l -> l.split(";")[1]));
            for (Map.Entry<String, List<String>> entry : mapa.entrySet()) {
                for (String s : entry.getValue()) {
                    if (s.contains(AUTOBUS)) {
                        vozilaObj.add(bilderi.get(AUTOBUS).build(entry.getValue()));
                        break;
                    } else if (s.contains(AUTOMOBIL)) {
                        vozilaObj.add(bilderi.get(AUTOMOBIL).build(entry.getValue()));
                        break;
                    } else if (s.contains(KAMION)) {
                        vozilaObj.add(bilderi.get(KAMION).build(entry.getValue()));
                        break;
                    }
                }
            }

            //vozilaObj.forEach(System.out::println); // ispis kreiranih vozila

        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // pokretanje vozila
        vozilaObj.forEach(Thread::start);
        for (Vozilo v : vozilaObj) {
            v.join();
        }
        redoslijed.forEach(System.out::println);
        long sum = vozilaObj.stream().mapToLong(v -> v.duration).sum();
        System.out.println("Prosjecno vrijeme kretanja vozila: " + sum / vozilaObj.size());
    }
}
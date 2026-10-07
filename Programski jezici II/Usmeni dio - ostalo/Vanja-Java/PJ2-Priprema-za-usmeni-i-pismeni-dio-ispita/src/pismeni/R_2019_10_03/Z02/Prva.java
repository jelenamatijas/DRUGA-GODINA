package pismeni.R_2019_10_03.Z02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Prva extends Thread {

    String path;
    static HashMap<String, List<String>> mapa = new HashMap<>();

    public Prva(String path) {
        this.path = path;
    }

    public void run() {
        try {
            //List<String> lines = Files.readAllLines(Path.of("./words.txt"), StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "utf-8"));
            List<String> lines = br.lines().collect(Collectors.toList());
            for (String line : lines) {
                String slovo = line.substring(0, 1);
                if (mapa.containsKey(slovo)) {
                    mapa.get(slovo).add(line);
                } else {
                    List<String> lista = new ArrayList<>();
                    lista.add(line);
                    mapa.put(slovo, lista);
                }
            }
            System.out.println("Prva nit je isprocesirala " + lines.size() + " rijeci");
            (new Druga()).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
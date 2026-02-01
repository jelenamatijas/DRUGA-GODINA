package pismeni.R_2022_01_26.Z2;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.util.List;

public class Database extends Thread {

    String putanja;

    public Database(String putanja) {
        this.putanja = putanja;
        start();
    }

    @Override
    public void run() {

        // deserijalizuje fajl iz foldera sa današnjim datumom
        String date = LocalDate.now().toString();
        String bin = "." + File.separator + date + File.separator + new File(putanja).getName().split("\\.")[0] + ".bin";
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(bin))) {
            List<String> lines = (List<String>) ois.readObject();
            for (String line : lines) {
                String[] parts = line.split("");
                for (String znak : parts) {
                    new Character(znak).join();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
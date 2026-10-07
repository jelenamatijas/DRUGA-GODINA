package parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import model.*;

public class NarudzbenicaParser extends Parser<Narudzbenica> {

    public NarudzbenicaParser(String path) {
        super();
        this.setPath(path);
    }

    @Override
    public void run() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(getPath()));
            for (String line : lines) {
                System.out.println("NP>> Obrada: " + line);

                checkWait();

                if (line.startsWith(DataTypeEnum.NARUDZBENICA.toString())) {
                    Narudzbenica object = new Narudzbenica().parse(line);
                    if (!getData().containsKey(object.getSifra())) {
                        getData().put(object.getSifra(), object);

                        try {
                            sleep(SLEEP_TIME);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

                if (line.startsWith(DataTypeEnum.NARUDZBENICA_STAVKA.toString())) {
                    NarudzbenicaStavka object = new NarudzbenicaStavka().parse(line);
                    if (getData().containsKey(object.getSifra())) {
                        getData().get(object.getSifra()).getStavke().add(object);

                        try {
                            Thread.sleep(SLEEP_TIME);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("Narudzbenica parser je zavrsio");
    }
}
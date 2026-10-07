package pismeni.R_2022_06_15.Z02;

import java.io.Serializable;
import java.util.List;

public class Profesor implements Serializable {
    public String ime;
    public String prezime;
    public String jmb;
    public List<String> predmetId;

    public Profesor(String ime, String prezime, String jmb, List<String> predmetId) {
        this.ime = ime;
        this.prezime = prezime;
        this.jmb = jmb;
        this.predmetId = predmetId;
    }
}
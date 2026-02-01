package pismeni.R_2022_06_15.Z02;

import java.io.Serializable;
import java.util.List;

public class Student implements Serializable {
    public String ime;
    public String prezime;
    public String brojIndeksa;
    public List<String> predmetId;

    public Student(String ime, String prezime, String brojIndeksa, List<String> predmetId) {
        this.ime = ime;
        this.prezime = prezime;
        this.brojIndeksa = brojIndeksa;
        this.predmetId =predmetId;
    }
}
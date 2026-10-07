package pismeni.R_2021_09_08.Z02;

public class Student {

    public String ime;
    public String prezime;
    public String brIndeksa;
    public int godinaRodj;
    public int godinaStudija;
    public double ocjena;

    public Student(String ime, String prezime, String brIndeksa, int godinaRodj, int godinaStudija, double ocjena) {
        this.ime = ime;
        this.prezime = prezime;
        this.brIndeksa = brIndeksa;
        this.godinaRodj = godinaRodj;
        this.godinaStudija = godinaStudija;
        this.ocjena = ocjena;
    }

    public String toString() {
        return ime + " " + prezime + " brIndeksa " + brIndeksa + " rodj: " + godinaRodj + " studija: " + godinaStudija +
                " " + ocjena;
    }

}
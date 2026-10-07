public class Knjiga {

    private String naslov;
    private String autor;
    private int brojStranica;
    private int godinaIzdanja;

    private static int redniBroj = 0;

    public Knjiga() {
        this.naslov = "Naslov" + redniBroj;
        this.autor = "Autor" + redniBroj;
        this.brojStranica = redniBroj;
        this.godinaIzdanja = redniBroj;
        redniBroj++;
    }

    @Override
    public String toString() {
        return "Knjiga{naslov=" + this.naslov + ", autor=" + this.autor + ", brojStranica=" + this.brojStranica
                + ", godinaIzdanja=" + this.godinaIzdanja + "}";
    }
}
package src.messages;

public class Message {
    private String imeIgraca;
    private String nazivTima;
    private long vrijemePogotka;
    private int brojPoena;
    public static int rezultat;

    public Message(String imeIgraca, String nazivTima, long vrijemePogotka, int brojPoena){
        this.imeIgraca = imeIgraca;
        this.nazivTima = nazivTima;
        this.vrijemePogotka = vrijemePogotka;
        this.brojPoena = brojPoena;
    }

    @Override
    public String toString(){
        return "Igrac{Ime: " + imeIgraca + " Naziv tima: " + nazivTima + " Vrijeme pogotka: " + vrijemePogotka
             + " Broj poena: " + brojPoena + "}";
    }
}

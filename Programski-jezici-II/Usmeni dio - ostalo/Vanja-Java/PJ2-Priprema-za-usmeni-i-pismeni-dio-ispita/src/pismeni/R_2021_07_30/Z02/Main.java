package pismeni.R_2021_07_30.Z02;

public class Main {
    public static void main(String[] args) {
        Alarm a1 = new Alarm();
        Alarm a2 = new Alarm();
        Alarm a3 = new Alarm();

        Obavjestenje o1 = new Obavjestenje();
        Obavjestenje o2 = new Obavjestenje();
        Obavjestenje o3 = new Obavjestenje();

        Skladiste<Alarm> s1 = new Skladiste<>();
        Skladiste<Obavjestenje> s2 = new Skladiste<>();
        s1.dodaj(a1);
        s1.dodaj(a2);
        s1.dodaj(a3);
        s1.ispis();
        s2.dodaj(o1);
        s2.dodaj(o2);
        s2.dodaj(o3);
        s2.ispis();


    }
}
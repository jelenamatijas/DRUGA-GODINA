package pismeni.R_2023_04_26.Z3;

import java.util.Random;

public class Student implements Comparable<Student> {

    String ime;
    String prezime;
    String indeks;
    double prosjek;

    public Student() {
        Random rand = new Random();
        ime = (char) (rand.nextInt(26) + 'a') + "ime";
        prezime = (char) (rand.nextInt(26) + 'a') + "prezime";
        indeks = String.valueOf(rand.nextInt(2000) + 1000);
        prosjek = rand.nextDouble() * 4.00 + 6.00;
    }

    @Override
    public int compareTo(Student s) {
        return Double.compare(this.prosjek, s.prosjek);
    }

    public boolean equals(Student s) {
        return this.equals(s);
    }


    @Override
    public String toString() {
        return ime + " " + prezime + " " + indeks + " " + prosjek;
    }
}
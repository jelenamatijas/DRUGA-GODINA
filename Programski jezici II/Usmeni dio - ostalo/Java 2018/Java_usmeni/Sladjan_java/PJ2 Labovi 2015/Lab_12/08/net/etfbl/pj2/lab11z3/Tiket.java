package net.etfbl.pj2.lab11z3;

import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Igor
 */
public class Tiket {

    private static int brojTiketa = 0;
    private int id;
    private Date datumProdaje;
    private int brojPogodjenihBrojeva = 0;
    private Integer [] brojevi = new Integer[6];
    
    public Tiket(int id) {
        this.id = id;
    }

    public Tiket() {
        id = brojTiketa++;
        datumProdaje = new Date();

        Scanner scan = new Scanner(System.in);
        int ukupnoBrojeva = 6;
        while (ukupnoBrojeva > 0) {
            System.out.println("Unesite broj u intervalu od 1 do 45:");
            int br = scan.nextInt();
            if (br > 0 && br <= 45 && !Arrays.asList(brojevi).contains(br)) {
                ukupnoBrojeva--;
                brojevi[ukupnoBrojeva] = br;
            }

        }
        System.out.println(this);
    }

    public int getId() {
        return id;
    }

    public int getBrojPogodjenihBrojeva() {
        return brojPogodjenihBrojeva;
    }

    public void setBrojPogodjenihBrojeva(int brojPogodjenihBrojeva) {
        this.brojPogodjenihBrojeva = brojPogodjenihBrojeva;
    }

    public Date getDatumProdaje() {
        return datumProdaje;
    }

    public Integer[] getBrojevi() {
        return brojevi;
    }

    @Override
    public String toString() {
        return "Tiket " + "id=" + id + ", datumProdaje=" + datumProdaje + ", brojevi=" + Arrays.toString(brojevi);
    }

    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tiket other = (Tiket) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}

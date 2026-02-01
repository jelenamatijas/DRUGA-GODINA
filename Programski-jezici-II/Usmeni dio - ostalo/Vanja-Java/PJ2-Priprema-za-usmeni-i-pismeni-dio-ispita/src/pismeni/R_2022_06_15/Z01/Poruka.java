package pismeni.R_2022_06_15.Z01;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Poruka {

    public String jmbPosiljaoca;
    public String jmbPrimalac;
    public String vrijemeSlanja; // dd/MM/yyyy HH:mm:ss
    public String tekst;

    public Poruka(String jmbPosiljaoca, String jmbPrimalac, String tekst){
        this.jmbPosiljaoca = jmbPosiljaoca;
        this.jmbPrimalac = jmbPrimalac;
        this.tekst = tekst;
        vrijemeSlanja = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
    }

}
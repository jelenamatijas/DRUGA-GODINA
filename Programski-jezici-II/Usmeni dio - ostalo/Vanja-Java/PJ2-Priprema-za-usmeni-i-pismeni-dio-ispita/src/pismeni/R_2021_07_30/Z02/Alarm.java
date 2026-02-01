package pismeni.R_2021_07_30.Z02;

import java.util.*;

public class Alarm extends Podatak {
    String id;
    String opis;
    Date datum;

    public Alarm(){
        id = "" + System.currentTimeMillis();
        opis = "OPIS";
        datum = new Date();
    }

    public void akcija(){
        System.out.println(id + " " + opis);
    }

    public String toString(){
        return "ALARM: " + id + " " + opis + " " + datum;
    }
}
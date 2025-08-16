package osoba;

import osoba.osoba_interface.*;

public class Pilot extends Osoba implements SavladajStijenuInterface{
    public Pilot(String ime, int energija){
        super(ime, energija);
    }

    @Override 
    public String toString(){
        return super.toString();
    }

    public void savladajStijenu(){
        System.out.println(this + " savladava stijenu.");
    }
}

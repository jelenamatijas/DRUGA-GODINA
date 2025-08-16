package osoba;

import osoba.osoba_interface.*;

public class Planinar extends Osoba implements SavladajStijenuInterface, SavladajVoduInterface{
    public Planinar(String ime, int energija){
        super(ime, energija);
    }

    @Override 
    public String toString(){
        return super.toString();
    }

    public void savladajStijenu(){
        System.out.println(this + " savladava stijenu.");
    }

    public void savladajVodu(){
        System.out.println(this + " savladava vodu.");
    }
}

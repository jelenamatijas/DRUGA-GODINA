package osoba;

import osoba.osoba_interface.*;

public class Vatrogasac extends Osoba implements SavladajVatruInterface, SavladajVoduInterface{
    public Vatrogasac(String ime, int energija){
        super(ime, energija);
    }

    @Override 
    public String toString(){
        return super.toString();
    }

    public void savladajVatru(){
        System.out.println(this + " savladava vatru.");
    }

    public void savladajVodu(){
        System.out.println(this + " savladava vodu.");
    }
}

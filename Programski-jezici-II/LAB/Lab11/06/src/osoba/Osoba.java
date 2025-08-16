package osoba;

import java.util.Random;

import elementi.*;
import mapa.Mapa;
import osoba.osoba_interface.*;

public class Osoba extends Thread implements Comparable<Osoba>{
    private String ime;
    private int energija;
    Random rand = new Random();

    public Osoba(String ime, int energija){
        this.energija = energija;
        this.ime = ime;
    }   

    public int getEnergija(){
        return energija;
    }

    @Override
    public String toString(){
        return "Osoba{ime: " + ime + " energija: " + energija + "}";
    }

    @Override
    public int compareTo(Osoba o){
        return Integer.compare(o.getEnergija(),this.energija);
    }

    @Override
    public void run(){
        try {
            boolean mozeSavladati = true;
            for(int i=0; i<100; i++) {
                if(mozeSavladati){
                    Element element = Mapa.MAPA[i];
                    if(element != null){   
                        synchronized(element){
                            if((element instanceof Vatra) && (this instanceof SavladajVatruInterface)){
                                System.out.println("Igrac " + this + " je na polju " + (i+1) + ". " + ((Vatra)element).vatraGori());
                                if(rand.nextInt(100) <= 50){
                                    ((SavladajVatruInterface)this).savladajVatru();
                                    Mapa.MAPA[i] = null;
                                    System.out.println(this + " nastavlja dalje.");
                                }else{
                                    mozeSavladati = false;
                                }
                            }

                            if((element instanceof Voda) && (this instanceof SavladajVoduInterface)){
                                System.out.println("Igrac " + this + " je na polju " + (i+1) + ". " + ((Voda)element).vodaSlana());
                                if(rand.nextInt(100) <= 70){
                                    ((SavladajVoduInterface)this).savladajVodu();
                                    Mapa.MAPA[i] = null;
                                    System.out.println(this + " nastavlja dalje.");
                                }else{
                                    mozeSavladati = false;
                                }
                            }

                            if((element instanceof Stijena) && (this instanceof SavladajStijenuInterface)){
                                System.out.println("Igrac " + this + " je na polju " + (i+1) + ". Visina stijene je " + ((Stijena)element).getVisina());
                                if(rand.nextInt(100) <= 70){
                                    ((SavladajStijenuInterface)this).savladajStijenu();
                                    Mapa.MAPA[i] = null;
                                    System.out.println(this + " nastavlja dalje.");
                                }else{
                                    mozeSavladati = false;
                                }
                            }
                        }
                    }
                }
                else{
                    System.out.println(this + " nije uspjela prevazici prepreku.");
                    break;
                }
            }
            if(mozeSavladati){
                System.out.println(this + " je uspjesno savladala sve prepreke.");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

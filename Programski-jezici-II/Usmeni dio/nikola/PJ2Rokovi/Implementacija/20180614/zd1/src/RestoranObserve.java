package src;

import src.gost.*;
import java.util.ArrayList;

import static src.Main.TABELA_USLUGA;


public class RestoranObserve extends Thread{
	private ArrayList<Gost> gosti;

	public RestoranObserve(ArrayList<Gost> gosti){
		this.gosti = gosti;
	}
	
	public void run(){
		while(gosti.size() != 0){
			removeGost(gosti.remove(0));
			try{
				Thread.sleep(5000);
			} catch(Exception e){
			e.printStackTrace();
		}
		}
	}
	
	private void removeGost(Gost gost){
		System.out.println(gost + " ulazi u restoran");
		System.out.println(gost.getDatumRodjenja());
		System.out.println("Dodatna usluga " + gost.getdodatnaOpcija());
		if(gost instanceof ObicniGost){
			System.out.println("Obican gost placa 50% za usluge restorana sale te je naplaceno" + TABELA_USLUGA.get(gost.getdodatnaOpcija())*1.5);
			((ObicniGost)gost).novac -= TABELA_USLUGA.get(gost.getdodatnaOpcija())*1.5;
		} else{
			System.out.println("Biznis gost " + gost+ " kompanije" + ((GostFirme)gost).getFirma() + " placa normalnu cjenu za usluge kongresne sale te je naplaceno" + TABELA_USLUGA.get(gost.getdodatnaOpcija())*1.5);
			((GostFirme)gost).novac -= TABELA_USLUGA.get(gost.getdodatnaOpcija());
		}
			
			
	}
	
}
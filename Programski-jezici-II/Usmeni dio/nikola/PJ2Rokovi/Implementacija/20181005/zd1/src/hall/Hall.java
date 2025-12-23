package src.hall;

import src.visitor.*;
import java.util.Vector;

public class Hall implements Runnable {
	private int y, x;
	private int[] freeSeats;
	private String name;
	private Vector<Visitor> visitors;
	private static final double ADULT_PRICE = 6, KID_PRICE = 4;
	private int entered = 0;
	double lengthOfRun = (double)System.currentTimeMillis();
	double money = 0;
	public Hall(String name, int y, int x, Vector<Visitor> visitors){
		this.visitors = visitors;
		this.name = name;
		this.x = x;
		this.y = y;
		freeSeats = new int[y];
		for(int counter = 0; counter < y; counter++)
			freeSeats[counter] = x;
	}
	
	public void setVisitors(Vector<Visitor> visitors) { this.visitors = visitors; }
	
	@Override
		public void run(){
			try{
				
			int nextVisitor = 0;
			while(nextVisitor < visitors.size() - 1){
				int adequateRowIndex = -1;
				Visitor curr = visitors.get(nextVisitor);
				if(((Adult)curr).getKids() != 0){
					for(int i = 0; i < y; i++)
						if(freeSeats[i]  >= ((Adult)curr).getKids() + 1){
							adequateRowIndex = i;
							break;
						}
					if(adequateRowIndex == -1){
						System.out.println("Nema reda sa dovoljno mjesta za " + curr + " i " + ((Adult)curr).getKids() + " djece");
						nextVisitor += ((Adult)curr).getKids() + 1;
						continue;
					} else if(((Adult)curr).getMoney() < 4*((Adult)curr).getKids() + 6){
						System.out.println(curr + " nema dovoljno para  za sebe 	 " +  " i " + ((Adult)curr).getKids() + " djece");
						nextVisitor += ((Adult)curr).getKids() + 1;
						continue;
					} else {
						System.out.println("Ulazi " + curr + " na poziciju " + adequateRowIndex + " " +  (x - freeSeats[adequateRowIndex]) + " i  " + (((Adult)curr).getKids()) + " djece");
						freeSeats[adequateRowIndex]--;
						money += 4*((Adult)curr).getKids() + 6;
						int toCount = 0;
						entered += ((Adult)curr).getKids() + 1;
						nextVisitor++;
						Thread.sleep(1000);
						while(toCount < ((Adult)curr).getKids()){
							System.out.println("Ulazi djete  " + visitors.get(nextVisitor++) + " na poziciju " + adequateRowIndex + " " + (x - freeSeats[adequateRowIndex]));
							freeSeats[adequateRowIndex]--;
							Thread.sleep(1000);
							toCount++;
					}}} else {
							if(((Adult)curr).getMoney() < 6) {
								System.out.println(curr + " nema dovoljno para ");
								nextVisitor++;
								continue;
							} else {
						      for(int i = 0; i < y; i++)
									if(freeSeats[i]  > 0){
									adequateRowIndex = i;
									break;
							 }
							if(adequateRowIndex == -1){
								System.out.println("Nema mjesta sa dovoljno mjesta za " + curr);
								nextVisitor++;
								continue;
							} else {
								money += 6;
								entered++;
								freeSeats[adequateRowIndex]--;
								nextVisitor++;
								System.out.println("Ulazi " + curr + " na poziciju " + adequateRowIndex + " " +  (x - freeSeats[adequateRowIndex]));
								Thread.sleep(1000);
							}		
						}
					}
				}
			} catch(Exception e) {
				e.printStackTrace();
				System.out.println(name);
			}
	}
	
	
	
	@Override
	public String toString() {
		return "Naziv: " + name + " " +  "Broj osoba koje su usle: " + entered + " Vrijeme trajanja simulacije (u sekundama) " + (((double)System.currentTimeMillis() - lengthOfRun)/1000) + " Naplaceno: " + money + "KM";
	} 
}
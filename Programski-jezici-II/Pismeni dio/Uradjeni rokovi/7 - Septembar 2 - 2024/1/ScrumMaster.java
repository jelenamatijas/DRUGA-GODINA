import java.io.*;
import java.util.*;

class ScrumMaster extends ClanTima implements ScrumMasterInterface{
	
	public ScrumMaster(String a, String b, int c){
		super(a,b,c);
	}
	
	public void upisi(PriorityQueue<Zadatak> uradjeniZadaci, int sprintId){
		try(PrintWriter pw = new PrintWriter(new FileWriter("sprint_" + sprintId + ".txt", true))){
			for(Zadatak z : uradjeniZadaci){
				pw.println(z.toString());
			}
		}catch(IOException e){
			System.out.println("GRESKA pri upisu u fajl: " + "sprint_" + sprintId + ".txt");
		}
	}
	
	@Override
	public void scrumMaster(String id){
		System.out.println("Scrum master " + ime + " " + prezime + id + ".");
	}
}
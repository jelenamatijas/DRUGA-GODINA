import java.util.*;
import java.io.*;

abstract class Letjelica extends Thread{
	static int ID=1;
	int id;
	int red;
	
	public Letjelica(){
		id = ID++;
	}
	
	@Override
	public String toString(){
		return "LETJELICA: ID: " + id;
	}
	
	@Override
	public void run(){
		
	}
}
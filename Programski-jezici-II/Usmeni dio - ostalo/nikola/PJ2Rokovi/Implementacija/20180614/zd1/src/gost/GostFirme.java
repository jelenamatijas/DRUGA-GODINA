package src.gost;

import java.util.Random;

public class GostFirme extends Gost{
	private String firma;
	public Double novac;
	
	public GostFirme(){
		firma = "Firma" + Long.toHexString(Double.doubleToLongBits(new Random().nextDouble()));
		novac = new Random().nextDouble()*100 + 10;
		
	}
	
	public String getFirma(){
		return firma;
	}
	
	public Double getNovac(){
		return novac;
	}
	
	@Override 
	public String toString(){
		return "Biznis " + super.toString();
	}
	
}
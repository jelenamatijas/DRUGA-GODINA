
class GostTrosakFirme extends Gost implements FirmaInterface{
	String nazivFirme;
	
	public GostTrosakFirme(){
		super();
		nazivFirme = "Firma" + (Main.rand.nextInt(5)+1);
	}
	
	@Override
	public String toString(){
		return super.toString() + " Gost dolazi o trosku firme " + nazivFirme + ".";
	}
}
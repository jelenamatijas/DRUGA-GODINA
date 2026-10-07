
class RacunUDomacojValuti extends Racun{
	public RacunUDomacojValuti(String id, String imeVlasnika, int stanje){
		super(id, imeVlasnika, stanje);
	}
	
	@Override
	public String toString(){
		return super.toString() + "KM";
	}
}
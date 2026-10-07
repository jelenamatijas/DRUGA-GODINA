
class RacunUStranojValuti extends Racun implements StranaValutaInterface{
	public RacunUStranojValuti(String id, String imeVlasnika, int stanje){
		super(id, imeVlasnika, stanje);
	}
	
	@Override
	public String toString(){
		return super.toString() + "$";
	}
}
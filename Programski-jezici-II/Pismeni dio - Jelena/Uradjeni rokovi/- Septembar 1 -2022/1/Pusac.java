class Pusac extends Gost implements PusacInterface{
	Pusac(){
		super();
	}
	
	@Override
	public String toString(){
		return "ID gosta: " + id + " Novac: " + iznos;
	}
}
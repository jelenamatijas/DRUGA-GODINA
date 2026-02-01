class Voz extends Vozilo{
	static int idVoza = 1;
	public Voz(String naziv){
		super(naziv + idVoza);
		idVoza++;
	}
	
	@Override
	public String toString(){
		return super.toString();
	}
}
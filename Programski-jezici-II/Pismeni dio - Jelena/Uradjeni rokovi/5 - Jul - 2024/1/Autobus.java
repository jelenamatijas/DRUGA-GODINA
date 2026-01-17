class Autobus extends Vozilo{
	static int idAutobusa = 1;
	public Autobus(String naziv){
		super(naziv + idAutobusa);
		idAutobusa++;
	}
	
	@Override
	public String toString(){
		return super.toString();
	}
}
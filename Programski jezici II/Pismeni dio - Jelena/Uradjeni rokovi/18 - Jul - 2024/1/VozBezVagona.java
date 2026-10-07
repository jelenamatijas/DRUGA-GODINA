class VozBezVagona extends Voz implements BaterijskoPunjenjeInterface{
	int baterija;
	
	public VozBezVagona(){
		super("Voz_Bez_Vagona_");
		baterija = Main.rand.nextInt(50, 101);
	}
	
	@Override
	public void smanjiBateriju(){
		baterija--;
	}
	
	@Override
	public String toString(){
		return super.toString() + " Stanje baterije: " + baterija;
	}
}
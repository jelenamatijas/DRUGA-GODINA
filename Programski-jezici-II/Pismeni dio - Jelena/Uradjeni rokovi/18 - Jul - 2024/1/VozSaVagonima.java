class VozSaVagonima extends Voz implements VozSaVagonimaInterface{
	int brojVagona;
	public VozSaVagonima(){
		super("Voz_Sa_Vagonima_");
		setBrojVagona();
	}
	
	@Override
	public void setBrojVagona(){
		brojVagona = Main.rand.nextInt(1, 10);
	}

	@Override
	public String toString(){
		return super.toString() + " Broj vagona: " + brojVagona;
	}
}
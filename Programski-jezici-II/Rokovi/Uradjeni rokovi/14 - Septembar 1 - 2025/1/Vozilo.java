class Vozilo{
	int registarskaOznaka;
	String marka;
	String boja;
	String vrijemeUlaska;
	
	public Vozilo(){
		registarskaOznaka = Main.rand.nextInt(1000, 10000);
		marka = "Marka_" + Main.rand.nextInt(1, 6);
		boja = "Boja_" + Main.rand.nextInt(1, 11);
		vrijemeUlaska = String.valueOf(System.currentTimeMillis());
	}
	
	@Override
	public String toString(){
		return "Registarska oznaka: " + registarskaOznaka + " Marka: " + marka + " Boja: " + boja + " Vrijeme ulaska: " + vrijemeUlaska;
	}
	
}
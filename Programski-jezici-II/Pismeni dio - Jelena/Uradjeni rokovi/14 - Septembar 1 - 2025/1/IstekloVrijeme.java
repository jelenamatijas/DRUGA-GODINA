class IstekloVrijeme extends LaksiPrekrsaj{
	public IstekloVrijeme(){
		super("Isteklo vrijeme");
	}
	
	public void generisi(){
		for(int i=0; i< 10; i++){

			while(true){
				int x = Main.rand.nextInt(0,30);
				int y = Main.rand.nextInt(0,15);
				
				if(Main.mapa[x][y] == null){
					Main.mapa[x][y] = new String("ISTEKLO");
					break;
				}
			}
		}
	}
	
	@Override
	public String toString(){
		return super.toString();
	}
}
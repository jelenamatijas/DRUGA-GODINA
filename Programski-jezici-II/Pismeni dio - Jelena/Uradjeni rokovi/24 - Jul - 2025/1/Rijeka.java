
class Rijeka{
	int nivo;
	
	public Rijeka(){
		nivo = 70;
	}
	
	public void setNivo(){
		int x = Main.rand.nextInt(0, 101);
		if(x <= 20){
			nivo = Main.rand.nextInt(0,41);
		}else if( x > 20 && x <= 80){
			nivo = Main.rand.nextInt(41, 81);
		}else{
			nivo = Main.rand.nextInt(81, 101);
		}
	}
}
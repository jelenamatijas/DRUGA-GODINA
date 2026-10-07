
class GostLicniTrosak extends Gost{
	double novac;
	
	public GostLicniTrosak(){
		super();
		if(Main.rand.nextInt(101) > 30){
			imaKupon = false;
		}
		novac = Main.rand.nextDouble(2, 13);
	}
	
	@Override
	public String toString(){
		return super.toString() + " Gost dolazi o licnom trosku i posjeduje " + novac + "KM.";
	}
}
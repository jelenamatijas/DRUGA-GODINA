import java.io.File;

class VrijednosnaPosiljka extends Posiljka implements VrijednosnaPosiljkaInterface{
	int vrijednost;
	String valuta;
	static int id = 1;
	
	VrijednosnaPosiljka(){
		super(Main.rand.nextInt(10,101));
		if(id %3 == 0){
			valuta = "EUR";
		}else if(id%3==0){
			valuta = "BAM";
		}else{
			valuta = "USD";
		}
		vrijednost = Main.rand.nextInt(100, 1000);
	}
	
	@Override
	public String toString(){
		return super.toString() + " Vrijednosna posiljka -> " + vrijednost + valuta;
	}
}
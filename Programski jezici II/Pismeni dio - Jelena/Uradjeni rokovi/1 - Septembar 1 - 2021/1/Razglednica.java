import java.io.File;

class Razglednica extends Posiljka implements RazglednicaInterface{
	String sadrzaj;
	File vizuelniDio;
	static int id = 1;
	
	Razglednica(File f){
		super(Main.rand.nextInt(1,11));
		sadrzaj = "Sadrzaj razglednice broj " + id;
		vizuelniDio = f;
	}
	
	@Override
	public String toString(){
		return super.toString() + " Razglednica -> " + sadrzaj + " " + vizuelniDio.getName();
	}
}
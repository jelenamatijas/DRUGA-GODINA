
public class Auto {
	private boolean radi;

	public void upali(){
		radi = true;
	}

	public void ugasi(){
		radi = false;
	}

	public static void main(String[] args) {		
		Auto vw = new Auto(); 
		vw.upali();
		System.out.println(vw.radi);
		vw.ugasi();
		System.out.println(vw.radi);
		Auto audi = null;
		audi.upali();
	}
}

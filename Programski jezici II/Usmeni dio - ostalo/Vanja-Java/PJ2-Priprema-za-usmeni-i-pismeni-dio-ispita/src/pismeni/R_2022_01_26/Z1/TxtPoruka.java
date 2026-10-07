public class TxtPoruka extends Poruka{
	
	String txtFajl;
	
	public TxtPoruka(String txtFajl){
		super();
		this.txtFajl = txtFajl;
	}
	
	@Override
	public String toString(){
		return super.toString() + " " + txtFajl;
	}
}
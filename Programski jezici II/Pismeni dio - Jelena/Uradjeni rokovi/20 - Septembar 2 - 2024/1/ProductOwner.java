
class ProductOwner extends ClanTima implements ProductOwnerInterface{
	String opis;
	
	public ProductOwner(String a, String b, int c, String d){
		super(a,b,c);
		opis = d;
	}
	
	@Override
	public void productOwner(){
		System.out.println("Product owner " + ime + " " + prezime + " radi.");
	}
}
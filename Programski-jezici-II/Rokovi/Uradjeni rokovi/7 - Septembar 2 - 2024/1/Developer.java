

class Developer extends ClanTima implements DeveloperInterface{
	int br;
	
	public Developer(String a, String b, int c){
		super(a,b,c);
		br = 0;
	}
	
	@Override
	public String toString(){
		return super.toString() + " Broj uradjenih zadataka: " + br;
	}
	
	@Override
	public void developer(String s){
		System.out.println("Developer " + ime + " " + prezime + " radi na zadatku " + s + ".");
	}
}
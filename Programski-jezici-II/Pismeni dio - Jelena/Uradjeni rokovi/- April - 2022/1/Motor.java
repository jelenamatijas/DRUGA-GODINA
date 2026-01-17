class Motor{
	int id;
	static int ID = 1;
	String tip;
	
	String tipovi[] = {
		"DIZEL", "BENZIN", "ELEKTRICNI", "HIBRID"
	};
	
	Motor(){
		id = ID++;
		tip = tipovi[id%3];
	}
	
	@Override
	public String toString(){
		return tip;
	}
}
class G extends F{
	int i=1;
	long z=1;
	
	protected long metoda(){
		z=super.metoda();
		return i+z;
	}
	G napravi(){
		return new G();
	}
}